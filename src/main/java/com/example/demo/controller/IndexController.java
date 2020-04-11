package com.example.demo.controller;

import com.example.demo.entity.Article;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    ArticleService articleService;
    @GetMapping("/")
    public String index(Model model){
        //查询所有文章
        List<Article> articles = articleService.findAll();
        model.addAttribute("articles", articles);
        return "index";
    }
}
