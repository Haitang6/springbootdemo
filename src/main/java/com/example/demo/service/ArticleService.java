package com.example.demo.service;

import com.example.demo.dto.ArticleDto;
import com.example.demo.entity.Article;
import com.example.demo.entity.ArticleExample;
import com.example.demo.entity.Comment;
import com.example.demo.mapper.ArticleMapper;
import com.example.demo.utils.DataUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    public void insert(Article article) {
        articleMapper.insert(article);
    }

    public List<ArticleDto> findAll(int pageNum) {
        PageHelper.startPage(pageNum,2);
        ArticleExample articleExample = new ArticleExample();
        List<Article> articles = articleMapper.selectByExample(articleExample);
        ArrayList<ArticleDto> articleDtos = new ArrayList<>();
        for(Article article:articles){
            //日期格式转换
            String dateStr = DataUtils.dateToString(article.getGmtCreate(), "yyyy-MM-dd");
            //分割tags标签
            String[] tags= StringUtils.split(article.getTags(),",");
            ArticleDto articleDto = new ArticleDto();
            BeanUtils.copyProperties(article,articleDto);
            articleDto.setTags(tags);
            articleDto.setGmtCreate(dateStr);
            articleDtos.add(articleDto);
        }
        return articleDtos;
    }

    public ArticleDto findByAid(String aid) {
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria()
                .andAidEqualTo(aid);
        List<Article> articles = articleMapper.selectByExample(articleExample);
        if (articles == null){
            return null;
        }else {
            ArticleDto articleDto = new ArticleDto();
            String dateStr = DataUtils.dateToString(articles.get(0).getGmtCreate(), "yyyy-MM-dd HH:mm:ss");
            String[] tags= StringUtils.split(articles.get(0).getTags(),",");
            BeanUtils.copyProperties(articles.get(0),articleDto);
            articleDto.setTags(tags);
            articleDto.setGmtCreate(dateStr);
            return articleDto;
        }
    }
}
