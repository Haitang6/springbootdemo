package com.example.demo.enums;

public enum CommentTypeEnum {
    ARTICLE(1),
    COMMENT(2);
    private int type;

    CommentTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
