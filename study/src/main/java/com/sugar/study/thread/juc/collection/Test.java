package com.sugar.study.thread.juc.collection;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Description todo
 * @Author sugar
 * @Date 2022/1/11 5:13 PM
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) {

        String s = "202111";
        String s1 = "202210";

        System.out.println(s.compareTo(s1));
        int count = 1;
        System.out.println(++count);

        AtomicInteger count1 = new AtomicInteger(1);
        System.out.println(count1.getAndIncrement());
//        Unsafe unsafe = Unsafe.getUnsafe();

        LongAdder count2 = new LongAdder();
        System.out.println(count2);


    }
}
