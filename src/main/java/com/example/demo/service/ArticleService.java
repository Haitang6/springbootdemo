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
import java.util.List;

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

    public void insert(Article article) {
        articleMapper.insert(article);
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
}
