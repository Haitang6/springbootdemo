package com.example.demo.controller;

import com.example.demo.service.NotificationServeice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NotificationController {
    @Autowired
    NotificationServeice notificationServeice;

    @GetMapping("/read/{nid}")
    public String read (@PathVariable String nid){
        //通知改成已读
        notificationServeice.read(nid);
        //重定向到文章详情
        String aid=notificationServeice.findAidByNid(nid);
        return "redirect:/details/"+aid;
    }

}
