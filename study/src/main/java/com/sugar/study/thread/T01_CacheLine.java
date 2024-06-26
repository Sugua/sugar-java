package com.sugar.study.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @Description 缓存行
 * @Author sugar
 * @Date 2021/3/18 10:28 AM
 * @Version 1.0
 */
public class T01_CacheLine {
    public static long COUNT = 100000000L;


    private static class T {
        public volatile long x = 0l;
    }

    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);

        Thread t1 = new Thread(() -> {

            for (int i=0;i<COUNT;i++) {

                arr[0].x = i;
            }
            latch.countDown();

        });

        Thread t2 = new Thread(() -> {

            for (int i=0;i<COUNT;i++) {

                arr[1].x = i;
            }
            latch.countDown();

        });
        final long time = System.nanoTime();

        t1.start();
        t2.start();
        latch.await();
        System.out.println((System.nanoTime()-time)/1000000);

    }


}
