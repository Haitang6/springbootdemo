package com.example.demo.entity;

import java.util.Date;

public class Article {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.AID
     *
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
     */
    private String aid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.TITLE
     *
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.CONTEXT
     *
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
     */
    private String context;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.TYPE
     *
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.TAGS
     *
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
     */
    private String tags;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.IS_PUBLIC
     *
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
     */
    private String isPublic;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.GMT_CREATE
     *
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
     */
    private Date gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.COMMENT_COUNT
     *
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
     */
    private Integer commentCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.LIKE_COUNT
     *
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
     */
    private Integer likeCount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.AID
     *
     * @return the value of article.AID
     *
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
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
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
     */
    public void setAid(String aid) {
        this.aid = aid == null ? null : aid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.TITLE
     *
     * @return the value of article.TITLE
     *
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
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
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.CONTEXT
     *
     * @return the value of article.CONTEXT
     *
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
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
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
     */
    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.TYPE
     *
     * @return the value of article.TYPE
     *
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
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
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.TAGS
     *
     * @return the value of article.TAGS
     *
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
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
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
     */
    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.IS_PUBLIC
     *
     * @return the value of article.IS_PUBLIC
     *
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
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
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
     */
    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic == null ? null : isPublic.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.GMT_CREATE
     *
     * @return the value of article.GMT_CREATE
     *
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
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
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
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
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
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
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
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
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
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
     * @mbg.generated Wed Apr 15 23:01:44 CST 2020
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }
}