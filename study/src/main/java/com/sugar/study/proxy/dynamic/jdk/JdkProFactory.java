package com.sugar.study.proxy.dynamic.jdk;

import java.lang.reflect.Proxy;

/**
 * @Description todo
 * @Author sugar
 * @Date 2024/6/18 11:55 PM
 * @Version 1.0
 */
public class JdkProFactory {
    public static Object getProxy(Object target){
        ClassLoader classLoader = target.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
//        System.out.println(classLoader);
//        System.out.println(interfaces);
//        System.out.println(interfaces[0]);
        return Proxy.newProxyInstance(classLoader,
                interfaces,
                new DynamicJDKInvocation(target));
    }
}
