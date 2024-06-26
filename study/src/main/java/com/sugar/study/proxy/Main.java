package com.sugar.study.proxy;

import com.sugar.study.proxy.dynamic.cglib.AliSmsService;
import com.sugar.study.proxy.dynamic.cglib.CglibProxyFactory;
import com.sugar.study.proxy.dynamic.jdk.JdkProFactory;
import com.sugar.study.proxy.statics.SmsProxy;

/**
 * @Description todo
 * @Author sugar
 * @Date 2024/6/18 11:42 PM
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        //static proxy
        System.out.println("**************static proxy*******************");
        SmsService smsService = new SmsServiceImpl();
        SmsProxy smsProxy = new SmsProxy(smsService);
        smsProxy.send("java");

        //dynamic proxy jdk
        System.out.println("**************dynamic proxy for jdk*******************");
        SmsService jdkSmsService = (SmsService) JdkProFactory.getProxy(new SmsServiceImpl());
        jdkSmsService.send("dynamic proxy for jdk");

        //dynamic proxy cglib
        System.out.println("**************dynamic proxy for cglib**************");
        AliSmsService aliSmsService=(AliSmsService)CglibProxyFactory.getProxy(AliSmsService.class);
        aliSmsService.send("dynamic proxy for cglig");

    }
}
