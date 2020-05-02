package com.example.demo.mapper;

import com.example.demo.entity.Article;

import java.util.List;

public interface ArticleExtMapper {
    void incView(Article article);
    void incLikeCount(Article article);

    void incCollectCount(Article article);

    void decLikeCount(Article article);

    void decCollectCount(Article article);

    void incCommentCount(Article article);

    List<Article> selectByRegexp(Article article);
}
