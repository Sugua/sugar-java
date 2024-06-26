package com.sugar.study.proxy.dynamic.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description todo
 * @Author sugar
 * @Date 2024/6/19 12:12 AM
 * @Version 1.0
 */
public class CglibMethodInterceptor implements MethodInterceptor {

    /**
     *
     * @param o 被代理的对象(需要增强的对象）
     * @param method    被拦截的方法（需要增加的方法）
     * @param args      方法入参
     * @param methodProxy   用于调用原始方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("before method: "+method.getName());
        Object result = methodProxy.invokeSuper(o, args);
        System.out.println("after method; "+method.getName());
        return result;
    }
}
