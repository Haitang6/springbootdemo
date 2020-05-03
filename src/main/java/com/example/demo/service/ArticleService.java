package com.example.demo.service;

import com.example.demo.dto.ArticleDto;
import com.example.demo.dto.ArticleInDto;
import com.example.demo.entity.*;
import com.example.demo.enums.ArticleStatusEnum;
import com.example.demo.enums.NotificationStatusEnum;
import com.example.demo.enums.NotificationTypeEnum;
import com.example.demo.enums.UserArticleTypeEnum;
import com.example.demo.mapper.*;
import com.example.demo.utils.DataUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    ArticleTypeMapper articleTypeMapper;
    @Autowired
    NotificationMapper notificationMapper;

    //发布文章
    public void insert(ArticleInDto articleInDto, HttpServletRequest request) {
        if (StringUtils.isNotBlank(articleInDto.getAid())) {
            //重写草稿箱中文文章
            articleInDto.setGmtCreate(new Date());
            articleInDto.setCommentCount(0);
            articleInDto.setLikeCount(0);
            articleInDto.setViewCount(0);
            articleInDto.setCollectCount(0);
            articleInDto.setIsFinished(ArticleStatusEnum.FINISHED.getType());
            User user = (User) request.getSession().getAttribute("user");
            articleInDto.setUid(user.getUid());
            //更新数据库
            ArticleExample articleExample = new ArticleExample();
            articleExample.createCriteria()
                    .andAidEqualTo(articleInDto.getAid());
            //articleInDto相同类型转换成article
            Article article = new Article();
            BeanUtils.copyProperties(articleInDto, article);
            articleMapper.updateByExample(article, articleExample);
            //用户未完成文章数量-1
            User user1 = new User();
            user1.setUid(user.getUid());
            user1.setUnfinishedArticleCount(1);
            userExtMapper.decUnfinishedArticleCount(user1);
            //用户完成数量+1
            user1.setFinishedArticleCount(1);
            userExtMapper.incFinishArticleCount(user1);

        } else {
            //发布新的文章
            articleInDto.setAid(UUID.randomUUID().toString());
            articleInDto.setGmtCreate(new Date());
            articleInDto.setCommentCount(0);
            articleInDto.setLikeCount(0);
            articleInDto.setViewCount(0);
            articleInDto.setCollectCount(0);
            articleInDto.setIsFinished("1");
            User user = (User) request.getSession().getAttribute("user");
            articleInDto.setUid(user.getUid());
            //插入数据
            Article article = new Article();
            BeanUtils.copyProperties(articleInDto, article);
            articleMapper.insert(article);
            //用户发布文章数+1
            User user1 = new User();
            user1.setUid(user.getUid());
            user1.setFinishedArticleCount(1);
            userExtMapper.incFinishArticleCount(user1);
            //文章type对应插入到数据库
            String[] types = articleInDto.getTypes();
            for (String typeid : types) {
                ArticleType articleType = new ArticleType();
                articleType.setAid(articleInDto.getAid());
                articleType.setTid(typeid);
                articleTypeMapper.insert(articleType);
            }
        }
    }

    //保存草稿
    public void insertUnfinished(Article article, HttpServletRequest request) {
        //草稿内容插入到数据库
        article.setAid(UUID.randomUUID().toString());
        article.setGmtCreate(new Date());
        article.setIsFinished(ArticleStatusEnum.UNFINISHED.getType());
        User user = (User) request.getSession().getAttribute("user");
        article.setUid(user.getUid());
        articleMapper.insert(article);
        //增加用户未完成文章数量
        User user1 = new User();
        user1.setUid(user.getUid());
        user1.setUnfinishedArticleCount(1);
        userExtMapper.incUnfinishedArticleCount(user1);

    }

    //查询所有
    public List<ArticleDto> findAll(int pageNum) {
        PageHelper.startPage(pageNum, 10);
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria()
                .andIsFinishedEqualTo(ArticleStatusEnum.FINISHED.getType());
        List<Article> articles = articleMapper.selectByExample(articleExample);
        ArrayList<ArticleDto> articleDtos = new ArrayList<>();
        for (Article article : articles) {
            //日期格式转换
            String dateStr = DataUtils.dateToString(article.getGmtCreate(), "yyyy-MM-dd");
            Timestamp timestamp = new Timestamp(article.getGmtCreate().getTime());
            long timestampTime = timestamp.getTime();
            //分割tags标签
            String[] tags = StringUtils.split(article.getTags(), ",");
            ArticleDto articleDto = new ArticleDto();
            BeanUtils.copyProperties(article, articleDto);
            articleDto.setTags(tags);
            articleDto.setGmtCreate(dateStr);
            articleDtos.add(articleDto);
            articleDto.setTimestampTime(timestampTime);
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
        if (articles == null) {
            return null;
        } else {
            ArticleDto articleDto = new ArticleDto();
            String dateStr = DataUtils.dateToString(articles.get(0).getGmtCreate(), "yyyy-MM-dd HH:mm:ss");
            Timestamp timestamp = new Timestamp(articles.get(0).getGmtCreate().getTime());
            long timestampTime = timestamp.getTime();
            String[] tags = StringUtils.split(articles.get(0).getTags(), ",");
            BeanUtils.copyProperties(articles.get(0), articleDto);
            articleDto.setTags(tags);
            articleDto.setGmtCreate(dateStr);
            articleDto.setCommentCount(comments.size());
            articleDto.setTimestampTime(timestampTime);
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
        User user = (User) request.getSession().getAttribute("user");
        userArticle.setUid(user.getUid());
        userArticleMapper.insert(userArticle);
        //更改article数据库中的数据
        Article article = new Article();
        article.setAid(userArticle.getAid());
        if (userArticle.getType() == UserArticleTypeEnum.LIKE.getType()) {
            //通知功能（喜欢文章）
            Notification notification = new Notification();
            notification.setNid(UUID.randomUUID().toString());
            notification.setNotifier(user.getUid());
            notification.setGmtCreate(new Date());
            notification.setNotifiedStatus(NotificationStatusEnum.UNREAD.getType());
            notification.setNotifierName(user.getPetName());
            notification.setAid(userArticle.getAid());
            notification.setNotifiedType(NotificationTypeEnum.LIKE.getType());
            //获取这篇文章的作者
            ArticleExample articleExample = new ArticleExample();
            articleExample.createCriteria()
                    .andAidEqualTo(userArticle.getAid());
            List<Article> articles = articleMapper.selectByExample(articleExample);
            notification.setReceiver(articles.get(0).getUid());
            notification.setArticleName(articles.get(0).getTitle());
            //通知插入到数据库
            notificationMapper.insert(notification);
            //文章的喜欢个数+1
            article.setLikeCount(1);
            articleExtMapper.incLikeCount(article);
        } else if (userArticle.getType() == UserArticleTypeEnum.COLLECTION.getType()) {
            //通知功能（收藏文章）
            Notification notification = new Notification();
            notification.setNid(UUID.randomUUID().toString());
            notification.setNotifier(user.getUid());
            notification.setGmtCreate(new Date());
            notification.setNotifiedStatus(NotificationStatusEnum.UNREAD.getType());
            notification.setNotifierName(user.getPetName());
            notification.setAid(userArticle.getAid());
            notification.setNotifiedType(NotificationTypeEnum.COLLECTION.getType());
            //获取这篇文章的作者
            ArticleExample articleExample = new ArticleExample();
            articleExample.createCriteria()
                    .andAidEqualTo(userArticle.getAid());
            List<Article> articles = articleMapper.selectByExample(articleExample);
            notification.setReceiver(articles.get(0).getUid());
            notification.setArticleName(articles.get(0).getTitle());
            //通知插入到数据库
            notificationMapper.insert(notification);
            //文章的收藏个数+1
            article.setCollectCount(1);
            articleExtMapper.incCollectCount(article);
        }
    }

    //取消点赞或者收藏功能
    public void userAndArticleDel(UserArticle userArticle, HttpServletRequest request) {
        //关联表中删除数据
        User user = (User) request.getSession().getAttribute("user");
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
        if (userArticle.getType() == UserArticleTypeEnum.LIKE.getType()) {
            article.setLikeCount(1);
            articleExtMapper.decLikeCount(article);
        } else if (userArticle.getType() == UserArticleTypeEnum.COLLECTION.getType()) {
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

    //相关推荐
    public List<Article> recomment(String aid) {
        //根据adi查询文章数据库信息
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria()
                .andAidEqualTo(aid);
        List<Article> articles = articleMapper.selectByExample(articleExample);
        //根据文章tag进行相关推荐
        String[] tags = StringUtils.split(articles.get(0).getTags(), ",");
        String regexp = Arrays.stream(tags).collect(Collectors.joining("|"));
        Article article = new Article();
        article.setTags(regexp);
        article.setAid(articles.get(0).getAid());
        List<Article> articles1=articleExtMapper.selectByRegexp(article);


        return articles1;
    }
}
