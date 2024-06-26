package com.sugar.study.proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description todo
 * @Author sugar
 * @Date 2024/6/18 11:48 PM
 * @Version 1.0
 */
public class DynamicJDKInvocation implements InvocationHandler {
    private final Object target;

    public DynamicJDKInvocation(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before method: " + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("after method: " + method.getName());

        return result;
    }
}
