package com.maozi.easywork.entity;

public class ResposeEntity {
    private String code;

    private Object data;

    private String message;

    public ResposeEntity(Object data){
        this.data = data;
        this.code = "0";
        this.message = "success";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String status) {
        this.code = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
