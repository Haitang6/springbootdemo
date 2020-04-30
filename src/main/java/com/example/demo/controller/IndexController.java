package com.example.demo.controller;

import com.example.demo.dto.ArticleDto;
import com.example.demo.entity.ArticleType;
import com.example.demo.entity.ArticleTypeExample;
import com.example.demo.entity.User;
import com.example.demo.mapper.ArticleTypeMapper;
import com.example.demo.service.ArticleService;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
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

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request,
                        @RequestParam(name = "pageNum", required = true, defaultValue = "1") int pageNum) {
        //查询所有文章
        List<ArticleDto> articleDtos = articleService.findAll(pageNum);
        PageInfo<ArticleDto> pageInfo = new PageInfo<>(articleDtos);
        pageInfo.setPageNum(pageNum);
        model.addAttribute("ArticlePageInfo", pageInfo);
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
