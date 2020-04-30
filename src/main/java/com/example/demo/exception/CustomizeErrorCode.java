package com.example.demo.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    NO_LOGIN(90001,"未登录"),
    ;
    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
