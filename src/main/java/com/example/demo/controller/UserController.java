package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
        user.setGmtCreate(new Date());
        user.setUid(UUID.randomUUID().toString());
        userService.register(user);
        return "redirect:/loginHtml";
    }

    @PostMapping("/reset")
    public String resetPwd(User user) {
        userService.resetPwd(user);
        return "redirect:/loginHtml";
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
