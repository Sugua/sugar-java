package com.sugar.blog.common.base;

import java.io.Serializable;

public class ResponseData<T> implements Serializable {

    private static final long serialVersionUID = 2716698352873690948L;

    private int code = 200;

    private String message = "success";

    private T data;

    public ResponseData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseData(T data) {
        this.data = data;
    }

    public ResponseData() {
    }

    public ResponseData(String message) {
        this.message = message;
    }


    public ResponseData<T> success(T data) {
        this.data = data;
        return this;

    }

    public ResponseData<T> success(T data, String message) {
        this.data = data;
        this.message = message;
        return this;
    }

    public ResponseData<T> fail(String message) {
        this.code = ResponseCode.SERVER_ERROR_CODE.getCode();
        this.message = message;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    public ResponseData<T> fail(String message, ResponseCode code) {
        this.code = code.getCode();
        this.message = message;
        return this;
    }

    public ResponseData<T> fail(String message, int code) {
        this.code = code;
        this.message = message;
        return this;
    }
}
