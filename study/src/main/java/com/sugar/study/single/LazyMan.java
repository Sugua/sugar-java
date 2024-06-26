package com.sugar.study.single;

import java.lang.reflect.Method;

/**
 * @Description 懒汉式 dcl
 * @Author sugar
 * @Date 2021/8/7 10:07 PM
 * @Version 1.0
 */
public class LazyMan {
    private LazyMan() {
    }

    private static volatile LazyMan lazyMan;

    //双重检测锁模式 懒汉式单列 dcl
    public static LazyMan getInstance() {
        if (lazyMan == null) {
            synchronized (LazyMan.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyMan();
                }
            }
        }

        return lazyMan;
    }
    public static void main(String[] args){
        LazyMan lazyMan = LazyMan.getInstance();

        Method[] declaredMethods = LazyMan.class.getDeclaredMethods();
    }
}
