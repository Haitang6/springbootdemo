package com.example.demo.enums;

public enum ArticleStatusEnum {
    FINISHED("1"),
    UNFINISHED("0");
    private String type;

    ArticleStatusEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
