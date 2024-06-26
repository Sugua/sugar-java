package com.sugar.study.thread.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @Description CountDownLatch demo
 * @Author sugar
 * @Date 2021/8/6 5:09 PM
 * @Version 1.0
 */
public class CountDownLatchDemo {

    public static void main(String[] args){

        CountDownLatch count = new CountDownLatch(10);
        for (int i = 1; i <= 10; i++) {

            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " go out ");
                count.countDown();
            },String.valueOf(i)).start();
        }
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Close door");
    }
}
