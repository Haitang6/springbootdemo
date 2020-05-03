package com.example.demo.dto;

import org.springframework.stereotype.Repository;
import java.util.Arrays;
import java.util.Date;

@Repository
public class ArticleInDto  {

    private String aid;
    private String Uid;
    private String title;
    private String context;
    private String tags;
    private String[] types;
    private String isPublic;
    private String isFinished;
    private Date gmtCreate;
    private Integer commentCount;
    private Integer likeCount;
    private Integer viewCount;
    private Integer collectCount;



    public String getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(String isFinished) {
        this.isFinished = isFinished;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }


    @Override
    public String toString() {
        return "ArticleInDto{" +
                "aid='" + aid + '\'' +
                ", Uid='" + Uid + '\'' +
                ", title='" + title + '\'' +
                ", context='" + context + '\'' +
                ", tags='" + tags + '\'' +
                ", types=" + Arrays.toString(types) +
                ", isPublic='" + isPublic + '\'' +
                ", isFinished='" + isFinished + '\'' +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", commentCount=" + commentCount +
                ", likeCount=" + likeCount +
                ", viewCount=" + viewCount +
                ", collectCount=" + collectCount +
                '}';
    }

}
