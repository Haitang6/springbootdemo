package com.example.demo.controller;

import com.example.demo.dto.ArticleDto;
import com.example.demo.entity.ArticleType;
import com.example.demo.entity.ArticleTypeExample;
import com.example.demo.enums.ArticleTypeEnum;
import com.example.demo.mapper.ArticleTypeMapper;
import com.example.demo.mapper.NotificationMapper;
import com.example.demo.service.ArticleService;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
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
        //查询最热消息
        List<ArticleDto> articleDtos = articleService.findAll(pageNum);
        Collections.sort(articleDtos);
        PageInfo<ArticleDto> pageInfo = new PageInfo<>(articleDtos);
        pageInfo.setPageNum(pageNum);
        model.addAttribute("ArticlePageInfo", pageInfo);
        model.addAttribute("typeTitle", ArticleTypeEnum.HOT_ARTICLE.getMessage());
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
        List<ArticleDto> articleDtos = new ArrayList<>();
        for (ArticleType articleType : articleTypes) {
            String aid = articleType.getAid();
            ArticleDto articleDto = articleService.findByAid(aid);
            articleDtos.add(articleDto);
        }
        Collections.sort(articleDtos);
        PageInfo<ArticleDto> pageInfo = new PageInfo<>(articleDtos);
        pageInfo.setPageNum(pageNum);
        model.addAttribute("ArticlePageInfo", pageInfo);
        if (tid.equals(ArticleTypeEnum.INTRODUCE.getCode())) {
            model.addAttribute("typeTitle", ArticleTypeEnum.INTRODUCE.getMessage());
            model.addAttribute("typeId", ArticleTypeEnum.INTRODUCE.getCode());
        } else if (tid.equals(ArticleTypeEnum.FOOD.getCode())) {
            model.addAttribute("typeTitle", ArticleTypeEnum.FOOD.getMessage());
            model.addAttribute("typeId", ArticleTypeEnum.FOOD.getCode());
        } else if (tid.equals(ArticleTypeEnum.ACCOMMODATION.getCode())) {
            model.addAttribute("typeTitle", ArticleTypeEnum.ACCOMMODATION.getMessage());
            model.addAttribute("typeId", ArticleTypeEnum.ACCOMMODATION.getCode());
        } else if (tid.equals(ArticleTypeEnum.PATH.getCode())) {
            model.addAttribute("typeTitle", ArticleTypeEnum.PATH.getMessage());
            model.addAttribute("typeId", ArticleTypeEnum.PATH.getCode());
        } else if (tid.equals(ArticleTypeEnum.STRATEGY.getCode())) {
            model.addAttribute("typeTitle", ArticleTypeEnum.STRATEGY.getMessage());
            model.addAttribute("typeId", ArticleTypeEnum.STRATEGY.getCode());
        } else if (tid.equals(ArticleTypeEnum.FRIEND.getCode())) {
            model.addAttribute("typeTitle", ArticleTypeEnum.FRIEND.getMessage());
            model.addAttribute("typeId", ArticleTypeEnum.FRIEND.getCode());
        } else if (tid.equals(ArticleTypeEnum.FARAWAY.getCode())) {
            model.addAttribute("typeTitle", ArticleTypeEnum.FARAWAY.getMessage());
            model.addAttribute("typeId", ArticleTypeEnum.FARAWAY.getCode());
        }
        return "index";
    }
}
