package com.example.demo.controller;
//import org.attoparser.dom.Comment;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.entity.Comment;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;
    @PostMapping("/comment")
    public String comment(@RequestBody Comment comment, HttpServletRequest request){

        commentService.comment(comment ,request);

        return "index";

    }
}
