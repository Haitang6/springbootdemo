package com.example.demo.service;

import com.example.demo.dto.ArticleDto;
import com.example.demo.entity.*;
import com.example.demo.mapper.*;
import com.example.demo.utils.DataUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleExtMapper articleExtMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserArticleMapper userArticleMapper;
    @Autowired
    UserExtMapper userExtMapper;

    public void insert(Article article,HttpServletRequest request) {
        if (StringUtils.isNotBlank(article.getAid())){
            //重写草稿箱中文文章
            article.setGmtCreate(new Date());
            article.setCommentCount(0);
            article.setLikeCount(0);
            article.setViewCount(0);
            article.setCollectCount(0);
            article.setIsFinished("1");
            User user = (User) request.getSession().getAttribute("user");
            article.setUid(user.getUid());
            //更新数据库
            ArticleExample articleExample = new ArticleExample();
            articleExample.createCriteria()
                    .andAidEqualTo(article.getAid());
            articleMapper.updateByExample(article,articleExample);
            //用户未完成文章数量-1
            User user1 = new User();
            user1.setUid(user.getUid());
            user1.setUnfinishedArticleCount(1);
            userExtMapper.decUnfinishedArticleCount(user1);
            //用户完成数量+1
            user1.setFinishedArticleCount(1);
            userExtMapper.incFinishArticleCount(user1);

        }else {
            //发布新的文章
            article.setAid(UUID.randomUUID().toString());
            article.setGmtCreate(new Date());
            article.setCommentCount(0);
            article.setLikeCount(0);
            article.setViewCount(0);
            article.setCollectCount(0);
            article.setIsFinished("1");
            User user = (User) request.getSession().getAttribute("user");
            article.setUid(user.getUid());
            //插入数据
            articleMapper.insert(article);
            //用户发布文章数+1
            User user1 = new User();
            user1.setUid(user.getUid());
            user1.setFinishedArticleCount(1);
            userExtMapper.incFinishArticleCount(user1);
        }

    }

    //保存草稿
    public void insertUnfinished(Article article, HttpServletRequest request) {
        //草稿内容插入到数据库
        article.setAid(UUID.randomUUID().toString());
        article.setGmtCreate(new Date());
        article.setIsFinished("0");
        User user = (User) request.getSession().getAttribute("user");
        article.setUid(user.getUid());
        articleMapper.insert(article);
        //增加用的为未完成文章数量
        User user1 = new User();
        user1.setUid(user.getUid());
        user1.setUnfinishedArticleCount(1);
        userExtMapper.incUnfinishedArticleCount(user1);

    }

    public List<ArticleDto> findAll(int pageNum) {
        PageHelper.startPage(pageNum,2);
        ArticleExample articleExample = new ArticleExample();
        List<Article> articles = articleMapper.selectByExample(articleExample);
        ArrayList<ArticleDto> articleDtos = new ArrayList<>();
        for(Article article:articles){
            //日期格式转换
            String dateStr = DataUtils.dateToString(article.getGmtCreate(), "yyyy-MM-dd");
            //分割tags标签
            String[] tags= StringUtils.split(article.getTags(),",");
            ArticleDto articleDto = new ArticleDto();
            BeanUtils.copyProperties(article,articleDto);
            articleDto.setTags(tags);
            articleDto.setGmtCreate(dateStr);
            articleDtos.add(articleDto);
        }
        return articleDtos;
    }

    //根据aid查询文章内容
    public ArticleDto findByAid(String aid) {
        //文章信息
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria()
                .andAidEqualTo(aid);
        List<Article> articles = articleMapper.selectByExample(articleExample);
        //文章下面的评论信息
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andPidEqualTo(aid);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if (articles == null){
            return null;
        }else {
            ArticleDto articleDto = new ArticleDto();
            String dateStr = DataUtils.dateToString(articles.get(0).getGmtCreate(), "yyyy-MM-dd HH:mm:ss");
            String[] tags= StringUtils.split(articles.get(0).getTags(),",");
            BeanUtils.copyProperties(articles.get(0),articleDto);
            articleDto.setTags(tags);
            articleDto.setGmtCreate(dateStr);
            articleDto.setCommentCount(comments.size());
            return articleDto;
        }
    }
    //增加浏览数
    public void incView(String aid) {
        Article article = new Article();
        article.setAid(aid);
        article.setViewCount(1);
        articleExtMapper.incView(article);
    }
    //点赞或者收藏功能
    public void userAndArticleInc(UserArticle userArticle, HttpServletRequest request) {
        //关联关系添加到表中
        User user=(User) request.getSession().getAttribute("user");
        userArticle.setUid(user.getUid());
        userArticleMapper.insert(userArticle);
        //更改article数据库中的数据
        Article article = new Article();
        article.setAid(userArticle.getAid());
        if (userArticle.getType()==1){
            article.setLikeCount(1);
            articleExtMapper.incLikeCount(article);
        }else if(userArticle.getType()==2){
            article.setCollectCount(1);
            articleExtMapper.incCollectCount(article);
        }

    }
    //取消点赞或者收藏功能
    public void userAndArticleDel(UserArticle userArticle, HttpServletRequest request) {
        //关联表中删除数据
        User user=(User) request.getSession().getAttribute("user");
        userArticle.setUid(user.getUid());
        UserArticleExample userArticleExample = new UserArticleExample();
        userArticleExample.createCriteria()
                .andAidEqualTo(userArticle.getAid())
                .andTypeEqualTo(userArticle.getType())
                .andUidEqualTo(user.getUid());
        userArticleMapper.deleteByExample(userArticleExample);
        //更改article数据库中的数据
        Article article = new Article();
        article.setAid(userArticle.getAid());
        if (userArticle.getType()==1){
            article.setLikeCount(1);
            articleExtMapper.decLikeCount(article);
        }else if(userArticle.getType()==2){
            article.setCollectCount(1);
            articleExtMapper.decCollectCount(article);
        }
    }

    //重写草稿箱
    public Article reWrite(String aid) {
        //根据aid查询内容
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria()
                .andAidEqualTo(aid);
        List<Article> articles = articleMapper.selectByExample(articleExample);
        return articles.get(0);

    }


}
