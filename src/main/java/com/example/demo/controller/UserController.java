package com.example.demo.controller;

import com.example.demo.dto.ArticleDto;
import com.example.demo.dto.ResultDto;
import com.example.demo.entity.Article;
import com.example.demo.entity.UpFan;
import com.example.demo.entity.User;
import com.example.demo.enums.ArticleStatusEnum;
import com.example.demo.enums.ArticleTypeEnum;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    //登录
    @PostMapping("/login")
    public String login(User user, HttpServletRequest request, HttpServletResponse response) {

        List<User> users = userService.login(user);
        if (users.size() == 0) {
            return "redirect:/loginHtml";
        } else {
            //登录成功，写入session
            User user1 = users.get(0);
            String token = UUID.randomUUID().toString();
            user1.setToken(token);
            //把token更新到数据库
            userService.updateToken(user1);
            response.addCookie(new Cookie("token", user1.getToken()));
            return "redirect:/";
        }
    }

    //注册
    @PostMapping("/register")
    public String register(User user) {
        userService.register(user);
        return "redirect:/loginHtml";
    }

    //重置密码
    @PostMapping("/reset")
    public String resetPwd(User user) {
        userService.resetPwd(user);
        return "redirect:/loginHtml";
    }

    //关注
    @ResponseBody
    @PostMapping("/upAndFanInc")
    public Object upAndFanInc(@RequestBody UpFan upFan, HttpServletRequest request) {
        userService.upAndFanInc(upFan, request);
        return ResultDto.success();
    }

    //取消关注
    @ResponseBody
    @PostMapping("/upAndFanDel")
    public Object upAndFanDel(@RequestBody UpFan upFan, HttpServletRequest request) {
        userService.upAndFanDel(upFan, request);
        return ResultDto.success();
    }

    //个人中心
    @GetMapping("/myself/{uid}")
    public String Myself(@PathVariable String uid, HttpServletRequest request, Model model) {
        //根据uid获取用户
        User user = userService.findUserByUid(uid);
        //查找已经完成的文章
        List<Article> articlesFinished = userService.findArticle(user, ArticleStatusEnum.FINISHED.getType());
        //查找草稿文章
        List<Article> articlesUnFinished = userService.findArticle(user, ArticleStatusEnum.UNFINISHED.getType());
        //查找我的关注
        List<User> uploaders = userService.findUploader(user);
        //查找我的粉丝
        List<User> fans = userService.findFan(user);
        model.addAttribute("articlesFinished", articlesFinished);
        model.addAttribute("articlesUnFinished", articlesUnFinished);
        model.addAttribute("uploaders", uploaders);
        model.addAttribute("fans", fans);
        model.addAttribute("user", user);
        return "myself";
    }

    //我的好友圈
    @GetMapping("/myUploader/{uid}")
    public String myUploader(@PathVariable String uid,HttpServletRequest request,Model model,
                             @RequestParam(name = "pageNum", required = true, defaultValue = "1") int pageNum){
        //查找我关注的人
        User user = new User();
        user.setUid(uid);
        List<User> uploaders = userService.findUploader(user);
        //查找uploader发表的文章
        List<ArticleDto> articleList=new ArrayList<>();
        for (User uploader:uploaders){
            user.setUid(uploader.getUid());
            List<ArticleDto> articleDtos = userService.findArticleDto(uploader,request);
            for (ArticleDto articleDto:articleDtos){
                articleDto.setUser(uploader);
            }
            articleList.addAll(articleDtos);
        }
        //按照发布时间排序
        Collections.sort(articleList);
        model.addAttribute("articleDtos",articleList);
        return "myUploader";
    }

    @GetMapping("/loginHtml")
    public String preLogin() {
        return "login";
    }

    @GetMapping("/registerHtml")
    public String preRegister() {
        return "register";
    }

    @GetMapping("/resetPwd")
    public String preReset() {
        return "resetPwd";
    }

    @GetMapping("/publishHtml")
    public String prePublish(Model model) {
        Article article = new Article();
        model.addAttribute("article", article);
        return "publish";
    }

}
