package com.example.demo.controller;

import com.example.demo.entity.Article;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.UUID;

@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;
    @PostMapping("/publishArticle")
    public String publishArticle(Article article){
        article.setAid(UUID.randomUUID().toString());
        article.setGmtCreate(new Date());
        article.setCommentCount(0);
        article.setLikeCount(0);
        articleService.insert(article);
        return "redirect:/";
    }

}
