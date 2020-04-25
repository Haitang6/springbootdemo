package com.example.demo.controller;

import com.example.demo.dto.ResultDto;
import com.example.demo.entity.UpFan;
import com.example.demo.entity.User;
import com.example.demo.entity.UserArticle;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @PostMapping("/register")
    public String register(User user) {

        userService.register(user);
        return "redirect:/loginHtml";
    }

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
    public String prePublish() {
        return "publish";
    }
}
