package com.example.demo.entity;

public class UserArticle {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_article.UID
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_article.AID
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    private String aid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_article.TYPE
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    private Integer type;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_article.UID
     *
     * @return the value of user_article.UID
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_article.UID
     *
     * @param uid the value for user_article.UID
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_article.AID
     *
     * @return the value of user_article.AID
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public String getAid() {
        return aid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_article.AID
     *
     * @param aid the value for user_article.AID
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public void setAid(String aid) {
        this.aid = aid == null ? null : aid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_article.TYPE
     *
     * @return the value of user_article.TYPE
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_article.TYPE
     *
     * @param type the value for user_article.TYPE
     *
     * @mbg.generated Mon Apr 27 23:02:39 CST 2020
     */
    public void setType(Integer type) {
        this.type = type;
    }
}