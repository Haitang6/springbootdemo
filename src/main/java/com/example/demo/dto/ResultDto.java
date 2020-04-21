package com.example.demo.dto;

public class ResultDto<T> {

    private Integer code;
    private String message;
    private T data;

     public static ResultDto success(){

        ResultDto<Object> resultDto = new ResultDto<>();
        resultDto.setCode(200);
        resultDto.setMessage("成功");
        return resultDto;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
