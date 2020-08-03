package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import com.example.demo.entity.*;
import com.example.demo.enums.CommentTypeEnum;
import com.example.demo.enums.NotificationStatusEnum;
import com.example.demo.enums.NotificationTypeEnum;
import com.example.demo.mapper.ArticleExtMapper;
import com.example.demo.mapper.ArticleMapper;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.NotificationMapper;
import com.example.demo.mongoEntity.Comment;
import com.example.demo.repository.CommentRepository;
import com.example.demo.utils.DataUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class CommentService {

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    ArticleExtMapper articleExtMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    NotificationMapper notificationMapper;
    @Autowired
    CommentRepository commentRepository;

//    public void comment(Comment comment, HttpServletRequest request) {
//        if (comment.getType() == CommentTypeEnum.ARTICLE.getType()){
//            //评论
//            comment.setCid(UUID.randomUUID().toString());
//            comment.setPid(comment.getPid());
//            comment.setGmtCreate(new Date());
//            comment.setReplayCount(0);
//            comment.setLikeCount(0);
//            //获取当前登录用户
//            User user = (User)request.getSession().getAttribute("user");
//            comment.setUid(user.getUid());
//            comment.setCommenterName(user.getPetName());
//            commentMapper.insert(comment);
//            //评论的文章commentCount+1
//            Article article = new Article();
//            article.setAid(comment.getPid());
//            article.setCommentCount(1);
//            articleExtMapper.incCommentCount(article);
//            //通知
//            Notification notification = new Notification();
//            notification.setNid(UUID.randomUUID().toString());
//            notification.setNotifier(comment.getUid());
//            notification.setReceiver(user.getUid());
//            notification.setAid(comment.getPid());
//            notification.setGmtCreate(new Date());
//            notification.setNotifierName(user.getPetName());
//            notification.setNotifiedStatus(NotificationStatusEnum.UNREAD.getType());
//            notification.setNotifiedType(NotificationTypeEnum.COMMENT.getType());
//            //获取评论文章的名称
//            ArticleExample articleExample = new ArticleExample();
//            articleExample.createCriteria()
//                    .andAidEqualTo(comment.getPid());
//            List<Article> articles = articleMapper.selectByExample(articleExample);
//            notification.setArticleName(articles.get(0).getTitle());
//            //通知插入数据库
//            notificationMapper.insert(notification);
//        }
//    }

    public void comment(com.example.demo.mongoEntity.Comment comment, HttpServletRequest request) {
        if (comment.getType() == CommentTypeEnum.ARTICLE.getType()) {
            //评论
            comment.setCid(UUID.randomUUID().toString());
            comment.setPid(comment.getPid());
            comment.setGmtCreate(new Date().toString());
            comment.setReplayCount(0);
            comment.setLikeCount(0);
            //获取当前登录用户
            User user = (User) request.getSession().getAttribute("user");
            comment.setUid(user.getUid());
            comment.setCommenterName(user.getPetName());
            comment.setCommenterName(user.getPetName());
//            commentMapper.insert(comment);
            commentRepository.insert(comment);
            //评论的文章commentCount+1
            Article article = new Article();
            article.setAid(comment.getPid());
            article.setCommentCount(1);
            articleExtMapper.incCommentCount(article);
            //通知
            Notification notification = new Notification();
            notification.setNid(UUID.randomUUID().toString());
            notification.setNotifier(comment.getUid());
            notification.setReceiver(user.getUid());
            notification.setAid(comment.getPid());
            notification.setGmtCreate(new Date());
            notification.setNotifierName(user.getPetName());
            notification.setNotifiedStatus(NotificationStatusEnum.UNREAD.getType());
            notification.setNotifiedType(NotificationTypeEnum.COMMENT.getType());
            //获取评论文章的名称
            ArticleExample articleExample = new ArticleExample();
            articleExample.createCriteria()
                    .andAidEqualTo(comment.getPid());
            List<Article> articles = articleMapper.selectByExample(articleExample);
            notification.setArticleName(articles.get(0).getTitle());
            //通知插入数据库
            notificationMapper.insert(notification);
        }
    }

//    public List<CommentDto> findCommentByAid(String aid) {
//        CommentExample commentExample = new CommentExample();
//        commentExample.createCriteria()
//                .andPidEqualTo(aid);
//        List<com.example.demo.entity.Comment> comments = commentMapper.selectByExample(commentExample);
//        List<CommentDto> commentDtos = new ArrayList<>();
//        for (com.example.demo.entity.Comment comment : comments) {
//            CommentDto commentDto = new CommentDto();
//            BeanUtils.copyProperties(comment, commentDto);
//            commentDto.setGmtCreate(DataUtils.dateToString(comment.getGmtCreate(), "yyyy-MM-dd HH:mm:ss"));
//            commentDtos.add(commentDto);
//        }
//        return commentDtos;
//    }

    public List<CommentDto> findCommentByAid(String aid) {
        List<Comment> comments = commentRepository.findByPid(aid);
        List<CommentDto> commentDtos = new ArrayList<>();
        for (  Comment comment : comments) {
            CommentDto commentDto = new CommentDto();
            BeanUtils.copyProperties(comment, commentDto);
//            commentDto.setGmtCreate(DataUtils.dateToString(comment.getGmtCreate(), "yyyy-MM-dd HH:mm:ss"));
            commentDtos.add(commentDto);
        }
        return commentDtos;
    }
}
