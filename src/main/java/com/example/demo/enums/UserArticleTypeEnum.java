package com.example.demo.enums;

public enum UserArticleTypeEnum {
    LIKE(1,"喜欢"),
    COLLECTION(2,"已收藏"),
    UNLIKE(3,"不喜欢"),
    UNCOLLECTION(4,"未收藏");
    private int type;
    private String description;

    UserArticleTypeEnum(int type, String description) {
        this.type = type;
        this.description = description;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
