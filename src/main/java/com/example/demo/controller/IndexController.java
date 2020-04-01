package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model){
//        User user = (User)request.getSession().getAttribute("user");
//        if (user == null){
//            return "index";
//        }else {
//            return "index";
//        }
return "index";
    }
}
