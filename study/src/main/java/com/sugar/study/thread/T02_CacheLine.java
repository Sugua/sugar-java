package com.sugar.study.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @Description 缓存行
 * @Author sugar
 * @Date 2021/3/18 10:28 AM
 * @Version 1.0
 */
public class T02_CacheLine {
    public static long COUNT = 100000000L;

    /**
     * 64位 读取
     */
    private static class T {
        public long p1,p2,p3,p4, p5,p6, p7;
        public volatile long x = 0l;
        public long p8,p9,p10,p11,p12,p13, p14;
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
