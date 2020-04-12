package com.example.demo.service;

import com.example.demo.dto.ArticleDto;
import com.example.demo.entity.Article;
import com.example.demo.entity.ArticleExample;
import com.example.demo.mapper.ArticleMapper;
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

    public List<ArticleDto> findAll() {
        ArticleExample articleExample = new ArticleExample();
        List<Article> articles = articleMapper.selectByExample(articleExample);
        ArrayList<ArticleDto> articleDtos = new ArrayList<>();
        for(Article article:articles){
            //分割tags标签
            String[] tags= StringUtils.split(article.getTags(),",");
            ArticleDto articleDto = new ArticleDto();
            BeanUtils.copyProperties(article,articleDto);
            articleDto.setTags(tags);
            articleDtos.add(articleDto);
        }

        return articleDtos;
    }
}
