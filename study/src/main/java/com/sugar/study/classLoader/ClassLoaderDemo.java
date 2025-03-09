package com.sugar.study.classLoader;

/**
 * @Description todo
 * @Author sugar
 * @Date 2022/4/22 4:10 PM
 * @Version 1.0
 */
public class ClassLoaderDemo {

    public static void main(String[] args){

        char i ='中';
        System.out.println(ClassLoaderDemo.class.getClassLoader());
        System.out.println(ClassLoaderDemo.class.getClassLoader().getParent());
        System.out.println(ClassLoaderDemo.class.getClassLoader().getParent().getParent());
    }
}
