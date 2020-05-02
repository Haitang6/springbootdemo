package com.example.demo.enums;

public enum ArticleTypeEnum {
    HOT_ARTICLE("0000","最热消息"),
    INTRODUCE("0001","景点官方"),
    FOOD("0002","绝味美食"),
    ACCOMMODATION("0003","舒适住宿"),
    PATH("0004","全程路线"),
    STRATEGY("0005","旅游攻略"),
    FRIEND("0006","寻同行"),
    FARAWAY("0007","理想远方")
    ;
    private String code;
    private String message;

    ArticleTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
