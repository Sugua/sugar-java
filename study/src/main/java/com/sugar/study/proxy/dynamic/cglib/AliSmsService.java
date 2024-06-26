package com.sugar.study.proxy.dynamic.cglib;

/**
 * @Description todo
 * @Author sugar
 * @Date 2024/6/19 12:25 AM
 * @Version 1.0
 */
public class AliSmsService {
    public String send(String message) {
        System.out.println("send ali message:" + message);
        return message;
    }
}
