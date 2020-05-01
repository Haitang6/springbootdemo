package com.example.demo.controller;

import com.example.demo.dto.ArticleDto;
import com.example.demo.dto.NotificationDto;
import com.example.demo.entity.*;
import com.example.demo.enums.NotificationStatusEnum;
import com.example.demo.enums.NotificationTypeEnum;
import com.example.demo.mapper.ArticleTypeMapper;
import com.example.demo.mapper.NotificationMapper;
import com.example.demo.service.ArticleService;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.Session;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;
    @Autowired
    ArticleTypeMapper articleTypeMapper;
    @Autowired
    NotificationMapper notificationMapper;
    @GetMapping("/")
    public String index(Model model, HttpServletRequest request,
                        @RequestParam(name = "pageNum", required = true, defaultValue = "1") int pageNum) {
        //查询所有文章
        List<ArticleDto> articleDtos = articleService.findAll(pageNum);
        PageInfo<ArticleDto> pageInfo = new PageInfo<>(articleDtos);
        pageInfo.setPageNum(pageNum);
        model.addAttribute("ArticlePageInfo", pageInfo);
        //获取当前登陆者
//        User user = (User) request.getSession().getAttribute("user");
//        //通知功能
//        NotificationExample notificationExample = new NotificationExample();
//        notificationExample.createCriteria()
//                .andReceiverEqualTo(user.getUid())
//                .andNotifiedStatusEqualTo(NotificationStatusEnum.UNREAD.getType());
//        List<Notification> notifications = notificationMapper.selectByExample(notificationExample);
//        List<NotificationDto> notificationDtos=new ArrayList<>();
//        for (Notification notification:notifications){
//            NotificationDto notificationDto = new NotificationDto();
//            BeanUtils.copyProperties(notification,notificationDto);
//            if (notification.getNotifiedType()==NotificationTypeEnum.COMMENT.getType()){
//                notificationDto.setType(NotificationTypeEnum.COMMENT.getDescribe());
//            }else if (notification.getNotifiedType()==NotificationTypeEnum.ATTENTION.getType()){
//                notificationDto.setType(NotificationTypeEnum.ATTENTION.getDescribe());
//            }else if (notification.getNotifiedType()==NotificationTypeEnum.LIKE.getType()){
//                notificationDto.setType(NotificationTypeEnum.LIKE.getDescribe());
//            }else if (notification.getNotifiedType()==NotificationTypeEnum.COLLECTION.getType()){
//                notificationDto.setType(NotificationTypeEnum.COLLECTION.getDescribe());
//            }
//            notificationDtos.add(notificationDto);
//        }
//        //通知个数
//        request.getSession().setAttribute("notificationCount",notificationDtos.size());
//        request.getSession().setAttribute("notificationDtos",notificationDtos);
        return "index";
    }
    @GetMapping("/{tid}")
    public String indexType(@PathVariable String tid, Model model, HttpServletRequest request,
                        @RequestParam(name = "pageNum", required = true, defaultValue = "1") int pageNum) {
        //查询这个类型下所有文章
        ArticleTypeExample articleTypeExample = new ArticleTypeExample();
        articleTypeExample.createCriteria()
                .andTidEqualTo(tid);
        List<ArticleType> articleTypes = articleTypeMapper.selectByExample(articleTypeExample);
        List<ArticleDto> articleDtos=new ArrayList<>();
        for (ArticleType articleType:articleTypes){
            String aid=articleType.getAid();
            ArticleDto articleDto = articleService.findByAid(aid);
            articleDtos.add(articleDto);
        }
        PageInfo<ArticleDto> pageInfo = new PageInfo<>(articleDtos);
        pageInfo.setPageNum(pageNum);
        model.addAttribute("ArticlePageInfo", pageInfo);
        return "index";
    }
 
}
