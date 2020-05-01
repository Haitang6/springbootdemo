package com.example.demo.enums;

public enum NotificationTypeEnum {
    LIKE(444,"点赞了你的文章"),
    COLLECTION(111,"收藏了你的文章"),
    COMMENT(222,"评论了你的文章"),
    ATTENTION(333,"关注了你");
    private int type;
    private String describe;

    NotificationTypeEnum(int type, String describe) {
        this.type = type;
        this.describe = describe;
    }

    public int getType() {
        return type;
    }

    public String getDescribe() {
        return describe;
    }
}
