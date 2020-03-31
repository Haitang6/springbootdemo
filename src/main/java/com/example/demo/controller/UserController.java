package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/loginHtml")
    public String login(){
        return "login";

    }
    @GetMapping("/registerHtml")
    public String register(){
        return "register";
    }
    @GetMapping("/resetPwd")
    public String reset(){
        return "resetPwd";
    }
}
