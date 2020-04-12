package com.example.demo.dto;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Date;

@Repository
public class ArticleDto {

    private String aid;
    private String title;
    private String context;
    private String type;
    private String[] tags;
    private String isPublic;
    private Date gmtCreate;
    private Integer commentCount;
    private Integer likeCount;
    public String getAid() {
        return aid;
    }
    public void setAid(String aid) {
        this.aid = aid == null ? null : aid.trim();
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }
    public String getContext() {
        return context;
    }
    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getIsPublic() {
            return isPublic;
        }
    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic == null ? null : isPublic.trim();
    }
    public Date getGmtCreate() {
        return gmtCreate;
    }
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
    public Integer getCommentCount() {
        return commentCount;
    }
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
    public Integer getLikeCount() {
        return likeCount;
    }
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    @Override
    public String toString() {
        return "ArticleDto{" +
                "aid='" + aid + '\'' +
                ", title='" + title + '\'' +
                ", context='" + context + '\'' +
                ", type='" + type + '\'' +
                ", tags=" + Arrays.toString(tags) +
                ", isPublic='" + isPublic + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", commentCount=" + commentCount +
                ", likeCount=" + likeCount +
                '}';
    }
}
