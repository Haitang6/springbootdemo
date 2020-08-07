package com.example.demo.controller;
import com.example.demo.dto.ResultDto;
import com.example.demo.mongoEntity.Comment;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;
    @Autowired
    RedisTemplate redisTemplate;
    @PostMapping("/comment")
    @ResponseBody
    public Object comment(@RequestBody Comment comment, HttpServletRequest request){

        commentService.comment(comment ,request);
        return ResultDto.success();
    }
}
