package com.sugar.study.proxy;

/**
 * @Description todo
 * @Author sugar
 * @Date 2024/6/18 11:46 PM
 * @Version 1.0
 */
public class SmsServiceImpl implements SmsService {
    @Override
    public String send(String message) {
        System.out.println("send message: " + message);
        return message;
    }
}
