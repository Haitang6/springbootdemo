package com.example.demo.controller;

import com.example.demo.dto.ArticleDto;
import com.example.demo.entity.Article;
import com.example.demo.entity.Comment;
import com.example.demo.service.ArticleService;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;
    @Autowired
    CommentService commentService;
    @PostMapping("/publishArticle")
    public String publishArticle(Article article) {
        article.setAid(UUID.randomUUID().toString());
        article.setGmtCreate(new Date());
        article.setCommentCount(0);
        article.setLikeCount(0);
        articleService.insert(article);
        return "redirect:/";
    }
    @GetMapping("/details/{aid}")
    public String details(@PathVariable String aid, Model model) {
        ArticleDto articleDto=articleService.findByAid(aid);
        List<Comment> comments=commentService.findCommentByAid(aid);
        model.addAttribute("article",articleDto);
        model.addAttribute("comments",comments);
        return "details";
    }


}
