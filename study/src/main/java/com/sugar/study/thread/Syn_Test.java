package com.sugar.study.thread;

import com.sugar.study.spring.Apple;
import org.openjdk.jol.info.ClassLayout;

/**
 * @Description
 * @Author sugar
 * @Date 2021/3/17 5:38 PM
 * @Version 1.0
 */
public class Syn_Test {

    public static void main(String[] args){
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

        Apple apple = new Apple();
        System.out.println(ClassLayout.parseInstance(apple).toPrintable());


    }

}
