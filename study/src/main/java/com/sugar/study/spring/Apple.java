package com.sugar.study.spring;

/**
 * @Description TODO
 * @Author sugar
 * @Date 2020/8/17 5:25 PM
 * @Version 1.0
 */
public class Apple implements Fruit {
    private String name;
    private Integer age;
    private char sex;


    public Apple(){
        System.out.println("######实例化 Apple######");
    }
    public void eat() {

        System.out.println("吃苹果🍎");
    }
}
