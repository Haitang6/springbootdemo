package com.example.demo.service;

import com.example.demo.entity.Article;
import com.example.demo.entity.ArticleExample;
import com.example.demo.mapper.ArticleMapper;
import org.apache.maven.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    public void insert(Article article) {
        articleMapper.insert(article);
    }

    public List<Article> findAll() {
        ArticleExample articleExample = new ArticleExample();
        List<Article> articles = articleMapper.selectByExample(articleExample);

        return articles;
    }
}
