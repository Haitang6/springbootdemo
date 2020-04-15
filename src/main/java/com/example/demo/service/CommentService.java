package com.example.demo.service;

import com.example.demo.entity.Comment;
import com.example.demo.entity.CommentExample;
import com.example.demo.entity.User;
import com.example.demo.mapper.CommentMapper;
import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    CommentMapper commentMapper;

    public void comment(Comment comment, HttpServletRequest request) {
        if (comment.getType() == 1){
            //评论
            comment.setCid(UUID.randomUUID().toString());
            comment.setPid(comment.getPid());
            comment.setGmtCreate(new Date());
            comment.setReplayCount(0);
            comment.setLikeCount(0);
            //获取当前登录用户
            User user = (User)request.getSession().getAttribute("user");
            comment.setUid(user.getUid());
            CommentExample commentExample = new CommentExample();
            commentMapper.insert(comment);
        }
    }

    public List<Comment> findCommentByAid(String aid) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andPidEqualTo(aid);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        return comments;
    }
}
