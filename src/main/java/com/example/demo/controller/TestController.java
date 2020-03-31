package com.example.demo.controller;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:wjup
 * @Date: 2018/9/26 0026
 * @Time: 14:42
 */
@RequestMapping("/springBoot")
@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String GetUser(){

//         userService.test();

         return "login";

    }
}