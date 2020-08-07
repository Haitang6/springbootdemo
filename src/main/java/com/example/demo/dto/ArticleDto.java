package com.example.demo.dto;

import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.io.Serializable;

@Repository
public class ArticleDto implements Comparable<ArticleDto> , Serializable {

    private String aid;
    private String Uid;
    private String title;
    private String context;
    private String[] tags;
    private String isPublic;
    private String gmtCreate;
    private long timestampTime;
    private Integer commentCount;
    private Integer likeCount;
    private Integer viewCount;
    private Integer collectCount;
    private String isFinished;
    private String isLike;
    private String isCollection;
    private User user;

    public ArticleDto() {

    }

    public long getTimestampTime() {
        return timestampTime;
    }

    public void setTimestampTime(long timestampTime) {
        this.timestampTime = timestampTime;
    }

    public String getIsLike() {
        return isLike;
    }

    public void setIsLike(String isLike) {
        this.isLike = isLike;
    }

    public String getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(String isCollection) {
        this.isCollection = isCollection;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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


    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
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


    public ArticleDto(String aid, String uid, String title, String context, String[] tags, String isPublic, String gmtCreate, Integer commentCount, Integer likeCount, Integer viewCount, Integer collectCount, String isFinished, User user) {
        this.aid = aid;
        Uid = uid;
        this.title = title;
        this.context = context;
        this.tags = tags;
        this.isPublic = isPublic;
        this.gmtCreate = gmtCreate;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
        this.collectCount = collectCount;
        this.isFinished = isFinished;
        this.user = user;
    }

    @Override
    public int compareTo(ArticleDto o) {
        Long  time=o.getTimestampTime()-this.getTimestampTime();
        int i = new Long(time).intValue();
        return i;
    }
}
