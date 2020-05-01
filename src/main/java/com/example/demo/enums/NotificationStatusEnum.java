package com.example.demo.enums;

public enum NotificationStatusEnum {
    UNREAD(0),
    READ(1);
    private Integer type;

    NotificationStatusEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
