package com.sugar.study.single;

/**
 * @Description 玩转单列，饿汉式
 * @Author sugar
 * @Date 2021/8/7 10:05 PM
 * @Version 1.0
 */
public class Hungry {


    private Hungry(){}


    private final static Hungry hungry = new Hungry();


    public static  Hungry getInstance(){
        return hungry;
    }

}
