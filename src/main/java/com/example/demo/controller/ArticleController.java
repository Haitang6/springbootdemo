package com.example.demo.controller;

import com.example.demo.dto.ArticleDto;
import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Article;
import com.example.demo.entity.User;
import com.example.demo.repository.TestRepository;
import com.example.demo.service.ArticleService;
import com.example.demo.service.CommentService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    UserService userService;

    @PostMapping("/publishArticle")
    public String publishArticle(Article article, HttpServletRequest request) {
        article.setAid(UUID.randomUUID().toString());
        article.setGmtCreate(new Date());
        article.setCommentCount(0);
        article.setLikeCount(0);
        article.setViewCount(0);
        article.setIsFinished("1");
        User user = (User) request.getSession().getAttribute("user");
        article.setUid(user.getUid());
        articleService.insert(article);
        return "redirect:/";
    }
    @GetMapping("/details/{aid}")
    public String details(@PathVariable String aid, Model model) {
        //增加浏览数
        articleService.incView(aid);
        ArticleDto articleDto=articleService.findByAid(aid);
        List<CommentDto> commentDtos=commentService.findCommentByAid(aid);
        //文章发布者信息
        User user=userService.findUserByUid(articleDto.getUid());

        model.addAttribute("article",articleDto);
        model.addAttribute("comments",commentDtos);
        model.addAttribute("creator",user);
        return "details";
    }


}
