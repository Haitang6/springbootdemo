package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.UID
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.PHONE_NUMBER
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    private String phoneNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.PET_NAME
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    private String petName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.PASSWORD
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.GMT_CREATE
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    private Date gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.PHOTO_URL
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    private String photoUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.TOKEN
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    private String token;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.FAN_COUNT
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    private Integer fanCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.UP_COUNT
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    private Integer upCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.FINISHED_ARTICLE_COUNT
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    private Integer finishedArticleCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.UNFINISHED_ARTICLE_COUNT
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    private Integer unfinishedArticleCount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.UID
     *
     * @return the value of user.UID
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.UID
     *
     * @param uid the value for user.UID
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.PHONE_NUMBER
     *
     * @return the value of user.PHONE_NUMBER
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.PHONE_NUMBER
     *
     * @param phoneNumber the value for user.PHONE_NUMBER
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.PET_NAME
     *
     * @return the value of user.PET_NAME
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    public String getPetName() {
        return petName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.PET_NAME
     *
     * @param petName the value for user.PET_NAME
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    public void setPetName(String petName) {
        this.petName = petName == null ? null : petName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.PASSWORD
     *
     * @return the value of user.PASSWORD
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.PASSWORD
     *
     * @param password the value for user.PASSWORD
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.GMT_CREATE
     *
     * @return the value of user.GMT_CREATE
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.GMT_CREATE
     *
     * @param gmtCreate the value for user.GMT_CREATE
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.PHOTO_URL
     *
     * @return the value of user.PHOTO_URL
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.PHOTO_URL
     *
     * @param photoUrl the value for user.PHOTO_URL
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl == null ? null : photoUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.TOKEN
     *
     * @return the value of user.TOKEN
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.TOKEN
     *
     * @param token the value for user.TOKEN
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.FAN_COUNT
     *
     * @return the value of user.FAN_COUNT
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    public Integer getFanCount() {
        return fanCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.FAN_COUNT
     *
     * @param fanCount the value for user.FAN_COUNT
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    public void setFanCount(Integer fanCount) {
        this.fanCount = fanCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.UP_COUNT
     *
     * @return the value of user.UP_COUNT
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    public Integer getUpCount() {
        return upCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.UP_COUNT
     *
     * @param upCount the value for user.UP_COUNT
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    public void setUpCount(Integer upCount) {
        this.upCount = upCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.FINISHED_ARTICLE_COUNT
     *
     * @return the value of user.FINISHED_ARTICLE_COUNT
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    public Integer getFinishedArticleCount() {
        return finishedArticleCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.FINISHED_ARTICLE_COUNT
     *
     * @param finishedArticleCount the value for user.FINISHED_ARTICLE_COUNT
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    public void setFinishedArticleCount(Integer finishedArticleCount) {
        this.finishedArticleCount = finishedArticleCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.UNFINISHED_ARTICLE_COUNT
     *
     * @return the value of user.UNFINISHED_ARTICLE_COUNT
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    public Integer getUnfinishedArticleCount() {
        return unfinishedArticleCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.UNFINISHED_ARTICLE_COUNT
     *
     * @param unfinishedArticleCount the value for user.UNFINISHED_ARTICLE_COUNT
     *
     * @mbg.generated Fri May 01 15:16:22 CST 2020
     */
    public void setUnfinishedArticleCount(Integer unfinishedArticleCount) {
        this.unfinishedArticleCount = unfinishedArticleCount;
    }
}