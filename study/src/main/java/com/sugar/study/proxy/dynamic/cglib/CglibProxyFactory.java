package com.sugar.study.proxy.dynamic.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @Description todo
 * @Author sugar
 * @Date 2024/6/19 12:19 AM
 * @Version 1.0
 */
public class CglibProxyFactory {
    public static Object getProxy(Class<?> clazz){
        //创建动态代理争强类
        Enhancer enhancer = new Enhancer();
        //设置类加载器
        enhancer.setClassLoader(clazz.getClassLoader());
        //设置被代理类
        enhancer.setSuperclass(clazz);
        //设置方法拦截器
        enhancer.setCallback(new CglibMethodInterceptor());
        return enhancer.create();


    }
}
