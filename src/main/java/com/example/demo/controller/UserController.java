package com.example.demo.controller;

import com.example.demo.dto.ResultDto;
import com.example.demo.entity.Article;
import com.example.demo.entity.UpFan;
import com.example.demo.entity.User;
import com.example.demo.entity.UserArticle;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public String login(User user, HttpServletRequest request, HttpServletResponse response) {

        List<User> users = userService.login(user);
        if (users.size() == 0) {
            return "redirect:/loginHtml";
        } else {
            //登录成功，写入session
            User user1= users.get(0);
            String token = UUID.randomUUID().toString();
            user1.setToken(token);
            //把token更新到数据库
            userService.updateToken(user1);
            response.addCookie(new Cookie("token",user1.getToken()));
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
    public Object upAndFanInc(@RequestBody UpFan upFan, HttpServletRequest request){
        userService.upAndFanInc(upFan,request);
        return ResultDto.success();
    }
    //取消关注
    @ResponseBody
    @PostMapping("/upAndFanDel")
    public Object upAndFanDel(@RequestBody UpFan upFan,HttpServletRequest request){
        userService.upAndFanDel(upFan,request);
        return ResultDto.success();
    }
    //个人中心
    @GetMapping("/myself")
    public String Myself(HttpServletRequest request, Model model) {
        //获取当前登陆者
        User user=(User)request.getSession().getAttribute("user");
        //查找已经完成的文章
        List<Article> articlesFinished=userService.findArticle(user,"1");
        //查找草稿文章
        List<Article> articlesUnFinished=userService.findArticle(user,"0");
        //查找我的关注
        List<User> uploaders=userService.findUploader(user);
        //查找我的粉丝
        List<User> fans=userService.findFan(user);
        model.addAttribute("articlesFinished",articlesFinished);
        model.addAttribute("articlesUnFinished",articlesUnFinished);
        model.addAttribute("uploaders",uploaders);
        model.addAttribute("fans",fans);
        model.addAttribute("user",user);
        return "myself";
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
        model.addAttribute("article",article);
        return "publish";
    }

}
