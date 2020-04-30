package com.example.demo.enums;

public enum UserArticleTypeEnum {
    LIKE(1),
    COLLECTION(2);
    private int type;

    UserArticleTypeEnum(int type) {
        this.type = type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
