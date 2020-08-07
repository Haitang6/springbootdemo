package com.example.demo.controller;

import com.example.demo.dto.ArticleDto;
import com.example.demo.dto.ArticleInDto;
import com.example.demo.dto.CommentDto;
import com.example.demo.dto.ResultDto;
import com.example.demo.entity.Article;
import com.example.demo.entity.User;
import com.example.demo.entity.UserArticle;
import com.example.demo.redis.ArticleDetailsRedis;
import com.example.demo.service.ArticleService;
import com.example.demo.service.CommentService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate redisTemplate;

    //发布文章或者重写草稿箱文章
    @PostMapping("/publishArticle")
    public String publishArticle(ArticleInDto articleInDto, HttpServletRequest request) {
        articleService.insert(articleInDto,request);
        return "redirect:/";
    }
    //草稿内容存入到数据库
    @PostMapping("/unFinishedArticle")
    @ResponseBody
    public Object unFinishedArticle(@RequestBody Article article ,HttpServletRequest request) {
        articleService.insertUnfinished(article,request);
        return ResultDto.success();
    }
    //重写草稿箱内容
    @GetMapping("/reWrite/{aid}")
    public String reWrite(@PathVariable String aid, Model model) {
        Article article = articleService.reWrite(aid);
        model.addAttribute("article",article);
        return "publish";
    }
    @GetMapping("/details/{aid}")
    public String details(@PathVariable String aid, Model model,HttpServletRequest request) {
        ArticleDetailsRedis articleDetails = (ArticleDetailsRedis)redisTemplate.opsForValue().get("article" + aid);
        if(articleDetails != null){
            ArticleDto articleDto = articleDetails.getArticleDto();
            List<CommentDto> commentDtos = articleDetails.getCommentDto();
            User user = articleDetails.getUser();
            String isLike = articleDetails.getIsLike();
            String isCollect = articleDetails.getIsCollect();
            String isAttention = articleDetails.getIsAttention();
            String isAuthor = articleDetails.getIsAuthor();
            List<Article> articles = articleDetails.getArticles();
            model.addAttribute("article",articleDto);
            model.addAttribute("comments",commentDtos);
            model.addAttribute("creator",user);
            model.addAttribute("isLike",isLike);
            model.addAttribute("isCollect",isCollect);
            model.addAttribute("isAttention",isAttention);
            model.addAttribute("isAuthor",isAuthor);
            model.addAttribute("articles",articles);
            return "details";
        }
        //增加浏览数
        articleService.incView(aid);
        //文章基本信息
        ArticleDto articleDto=articleService.findByAid(aid);
        //文章下面的评论信息
        List<CommentDto> commentDtos=commentService.findCommentByAid(aid);
        //文章发布者信息
        User user=userService.findUserByUid(articleDto.getUid());
        //判断读者时候给文章点过赞
        String isLike=userService.isLike(aid,request);
        //判断文章是否被当前读者收藏
        String isCollect=userService.isCollect(aid,request);
        //判断作者是否被当前读者关注
        String isAttention=userService.idAttention(aid,request);
        //判断当前文章的作者是否为当前的浏览者
        String isAuthor=userService.isAuthor(aid,request);
        //相关推荐
        List<Article> articles=articleService.recomment(aid);
        model.addAttribute("article",articleDto);
        model.addAttribute("comments",commentDtos);
        model.addAttribute("creator",user);
        model.addAttribute("isLike",isLike);
        model.addAttribute("isCollect",isCollect);
        model.addAttribute("isAttention",isAttention);
        model.addAttribute("isAuthor",isAuthor);
        model.addAttribute("articles",articles);

        ArticleDetailsRedis articleDetailsRedis = new ArticleDetailsRedis(articleDto, commentDtos, user, isLike, isCollect, isAttention, isAuthor, articles);
        redisTemplate.opsForValue().set("article"+aid,articleDetailsRedis);
        return "details";
    }
    //点赞或者收藏
    @ResponseBody
    @PostMapping("/userAndArticleInc")
    public Object userAndArticleInc(@RequestBody UserArticle userArticle,HttpServletRequest request){
        articleService.userAndArticleInc(userArticle,request);
        return ResultDto.success();
    }
    //取消点赞收藏
    @ResponseBody
    @PostMapping("/userAndArticleDel")
    public Object userAndArticleDel(@RequestBody UserArticle userArticle,HttpServletRequest request){
        articleService.userAndArticleDel(userArticle,request);
        return ResultDto.success();
    }


}
