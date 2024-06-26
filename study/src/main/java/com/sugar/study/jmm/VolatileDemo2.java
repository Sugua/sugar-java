package com.sugar.study.jmm;

/**
 * @Description 不保证原子性
 * @Author sugar
 * @Date 2021/8/7 9:48 PM
 * @Version 1.0
 */
public class VolatileDemo2 {
    private static volatile int num = 0;

    public static void add() {
        num++;
    }

    public static void main(String[] args) {


        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 10000; i1++) {
                    num++;
                }
            }).start();
        }

        while (Thread.activeCount() > 2) { //Java默认有2个线程一个是 main一个是 gc
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "  " + num);


    }
}
