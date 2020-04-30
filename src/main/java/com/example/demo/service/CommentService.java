package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Article;
import com.example.demo.entity.Comment;
import com.example.demo.entity.CommentExample;
import com.example.demo.entity.User;
import com.example.demo.enums.CommentTypeEnum;
import com.example.demo.mapper.ArticleExtMapper;
import com.example.demo.mapper.ArticleMapper;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.utils.DataUtils;
import com.mysql.cj.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    ArticleExtMapper  articleExtMapper;

    public void comment(Comment comment, HttpServletRequest request) {
        if (comment.getType() == CommentTypeEnum.ARTICLE.getType()){
            //评论
            comment.setCid(UUID.randomUUID().toString());
            comment.setPid(comment.getPid());
            comment.setGmtCreate(new Date());
            comment.setReplayCount(0);
            comment.setLikeCount(0);
            //获取当前登录用户
            User user = (User)request.getSession().getAttribute("user");
            comment.setUid(user.getUid());
            commentMapper.insert(comment);
            //评论的文章commentCount+1
            Article article = new Article();
            article.setAid(comment.getPid());
            article.setCommentCount(1);
            articleExtMapper.incCommentCount(article);
        }
    }

    public List<CommentDto> findCommentByAid(String aid) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andPidEqualTo(aid);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        List<CommentDto> commentDtos=new ArrayList<>();
        for (Comment comment:comments){
            CommentDto commentDto = new CommentDto();
            BeanUtils.copyProperties(comment,commentDto);
            commentDto.setGmtCreate(DataUtils.dateToString(comment.getGmtCreate(),"yyyy-MM-dd HH:mm:ss"));
            commentDtos.add(commentDto);
        }
        return commentDtos;
    }
}
