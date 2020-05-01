package com.example.demo.dto;

public class NotificationDto {

    private String nid;

    private String notifier;

    private String receiver;

    private String aid;

    private String notifierName;

    private String type;

    private String articleName;

    private Integer notifiedStatus;

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getNotifier() {
        return notifier;
    }

    public void setNotifier(String notifier) {
        this.notifier = notifier;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getNotifierName() {
        return notifierName;
    }

    public void setNotifierName(String notifierName) {
        this.notifierName = notifierName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public Integer getNotifiedStatus() {
        return notifiedStatus;
    }

    public void setNotifiedStatus(Integer notifiedStatus) {
        this.notifiedStatus = notifiedStatus;
    }

    @Override
    public String toString() {
        return "NotificationDto{" +
                "nid='" + nid + '\'' +
                ", notifier='" + notifier + '\'' +
                ", receiver='" + receiver + '\'' +
                ", aid='" + aid + '\'' +
                ", notifierName='" + notifierName + '\'' +
                ", type='" + type + '\'' +
                ", articleName='" + articleName + '\'' +
                ", notifiedStatus=" + notifiedStatus +
                '}';
    }
}