package com.sugar.study.proxy.statics;

import com.sugar.study.proxy.SmsService;

/**
 * @Description todo
 * @Author sugar
 * @Date 2024/6/18 11:37 PM
 * @Version 1.0
 */
public class SmsProxy implements SmsService {
    private final SmsService smsService;

    public SmsProxy(SmsService smsService) {
        this.smsService = smsService;

    }


    @Override
    public String send(String message) {
        System.out.println("before method send()");
        smsService.send(message);
        System.out.println("after method send()");
        return message;
    }
}
