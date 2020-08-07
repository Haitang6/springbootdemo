package com.example.demo.redis;

import com.example.demo.dto.ArticleDto;
import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Article;
import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class ArticleDetailsRedis implements Serializable {
    public ArticleDto articleDto;
    public List<CommentDto> commentDto;
    public User user;
    public String isLike;
    public String isCollect;
    public String isAttention;
    public String isAuthor;
    public List<Article> articles;

    public ArticleDetailsRedis(ArticleDto articleDto, List<CommentDto> commentDto, User user, String isLike, String isCollect, String isAttention, String isAuthor, List<Article> articles) {
        this.articleDto = articleDto;
        this.commentDto = commentDto;
        this.user = user;
        this.isLike = isLike;
        this.isCollect = isCollect;
        this.isAttention = isAttention;
        this.isAuthor = isAuthor;
        this.articles = articles;
    }

    public ArticleDetailsRedis() {
    }

    public ArticleDto getArticleDto() {
        return articleDto;
    }

    public void setArticleDto(ArticleDto articleDto) {
        this.articleDto = articleDto;
    }

    public List<CommentDto> getCommentDto() {
        return commentDto;
    }

    public void setCommentDto(List<CommentDto> commentDto) {
        this.commentDto = commentDto;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIsLike() {
        return isLike;
    }

    public void setIsLike(String isLike) {
        this.isLike = isLike;
    }

    public String getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(String isCollect) {
        this.isCollect = isCollect;
    }

    public String getIsAttention() {
        return isAttention;
    }

    public void setIsAttention(String isAttention) {
        this.isAttention = isAttention;
    }

    public String getIsAuthor() {
        return isAuthor;
    }

    public void setIsAuthor(String isAuthor) {
        this.isAuthor = isAuthor;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "ArticleDetailsRedis{" +
                "articleDto=" + articleDto +
                ", commentDto=" + commentDto +
                ", user=" + user +
                ", isLike='" + isLike + '\'' +
                ", isCollect='" + isCollect + '\'' +
                ", isAttention='" + isAttention + '\'' +
                ", isAuthor='" + isAuthor + '\'' +
                ", articles=" + articles +
                '}';
    }
}
