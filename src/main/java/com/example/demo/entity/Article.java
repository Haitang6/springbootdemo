package com.example.demo.entity;

import java.util.Date;

public class Article {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.AID
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    private String aid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.UID
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.CONTEXT
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    private String context;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.TITLE
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.TAGS
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    private String tags;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.TYPE
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.GMT_CREATE
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    private Date gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.COMMENT_COUNT
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    private Integer commentCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.LIKE_COUNT
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    private Integer likeCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.VIEW_COUNT
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    private Integer viewCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.COLLECT_COUNT
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    private Integer collectCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.IS_FINISHED
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    private String isFinished;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.IS_PUBLIC
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    private String isPublic;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.AID
     *
     * @return the value of article.AID
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public String getAid() {
        return aid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.AID
     *
     * @param aid the value for article.AID
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public void setAid(String aid) {
        this.aid = aid == null ? null : aid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.UID
     *
     * @return the value of article.UID
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.UID
     *
     * @param uid the value for article.UID
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.CONTEXT
     *
     * @return the value of article.CONTEXT
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public String getContext() {
        return context;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.CONTEXT
     *
     * @param context the value for article.CONTEXT
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.TITLE
     *
     * @return the value of article.TITLE
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.TITLE
     *
     * @param title the value for article.TITLE
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.TAGS
     *
     * @return the value of article.TAGS
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public String getTags() {
        return tags;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.TAGS
     *
     * @param tags the value for article.TAGS
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.TYPE
     *
     * @return the value of article.TYPE
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.TYPE
     *
     * @param type the value for article.TYPE
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.GMT_CREATE
     *
     * @return the value of article.GMT_CREATE
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.GMT_CREATE
     *
     * @param gmtCreate the value for article.GMT_CREATE
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.COMMENT_COUNT
     *
     * @return the value of article.COMMENT_COUNT
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.COMMENT_COUNT
     *
     * @param commentCount the value for article.COMMENT_COUNT
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.LIKE_COUNT
     *
     * @return the value of article.LIKE_COUNT
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.LIKE_COUNT
     *
     * @param likeCount the value for article.LIKE_COUNT
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.VIEW_COUNT
     *
     * @return the value of article.VIEW_COUNT
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public Integer getViewCount() {
        return viewCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.VIEW_COUNT
     *
     * @param viewCount the value for article.VIEW_COUNT
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.COLLECT_COUNT
     *
     * @return the value of article.COLLECT_COUNT
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public Integer getCollectCount() {
        return collectCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.COLLECT_COUNT
     *
     * @param collectCount the value for article.COLLECT_COUNT
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.IS_FINISHED
     *
     * @return the value of article.IS_FINISHED
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public String getIsFinished() {
        return isFinished;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.IS_FINISHED
     *
     * @param isFinished the value for article.IS_FINISHED
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public void setIsFinished(String isFinished) {
        this.isFinished = isFinished == null ? null : isFinished.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.IS_PUBLIC
     *
     * @return the value of article.IS_PUBLIC
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public String getIsPublic() {
        return isPublic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.IS_PUBLIC
     *
     * @param isPublic the value for article.IS_PUBLIC
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic == null ? null : isPublic.trim();
    }
}