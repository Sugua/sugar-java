package com.sugar.study.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 利用Atomic来保证原子性
 * @Author sugar
 * @Date 2021/8/7 9:57 PM
 * @Version 1.0
 */
public class VolatileDemo3 {

    private static volatile AtomicInteger num = new AtomicInteger();

    public static void add() {
        num.getAndIncrement();//方法cas
    }

    public static void main(String[] args) {


        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 10000; i1++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) { //Java默认有2个线程一个是 main一个是 gc
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "  " + num);


    }



}
