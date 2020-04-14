package com.example.demo.controller;

import com.example.demo.dto.ArticleDto;
import com.example.demo.entity.User;
import com.example.demo.service.ArticleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;
    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        //通过cookie获取登录态
        Cookie[] cookies = request.getCookies();
        if (cookies==null){

        }
        for (Cookie cookie:cookies){
            String name = cookie.getName();
            if ("token".equals(name)){
                String tokenValue = cookie.getValue();
                //如果根据tokenValue能查询到用户，就登录此用户
//                User user=userService.findByToken(tokenValue);
                if (userService.findByToken(tokenValue) == null){
                    //请到首页进行登录
                }else {
                    //把此用户放到session域
                    request.getSession().setAttribute("user", userService.findByToken(tokenValue));
                    break;
                }
            }
        }


        //查询所有文章
        List<ArticleDto> articleDtos = articleService.findAll();
        model.addAttribute("articles", articleDtos);
        return "index";
    }
}
