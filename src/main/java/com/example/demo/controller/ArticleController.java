package com.example.demo.controller;

import com.example.demo.dto.ArticleDto;
import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Article;
import com.example.demo.repository.TestRepository;
import com.example.demo.service.ArticleService;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;
    @Autowired
    CommentService commentService;
    @Autowired
    TestRepository testService;
//    @Autowired
//    ArticleRepository articleRepository;
//
//    @GetMapping("findAll")
//    public String findAll(){
//        Iterable article  = articleRepository.findAll();
//        System.out.println(article);
//        return "redirect:/";
//    }




    @PostMapping("/publishArticle")
    public String publishArticle(Article article) {
        article.setAid(UUID.randomUUID().toString());
        article.setGmtCreate(new Date());
        article.setCommentCount(0);
        article.setLikeCount(0);
        article.setViewCount(0);
        articleService.insert(article);
        return "redirect:/";
    }
    @GetMapping("/details/{aid}")
    public String details(@PathVariable String aid, Model model) {
        ArticleDto articleDto=articleService.findByAid(aid);
        List<CommentDto> commentDtos=commentService.findCommentByAid(aid);
        model.addAttribute("article",articleDto);
        model.addAttribute("comments",commentDtos);
        return "details";
    }


}
