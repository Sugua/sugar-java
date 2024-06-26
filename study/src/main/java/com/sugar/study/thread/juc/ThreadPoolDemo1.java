package com.sugar.study.thread.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description 线程池
 * 线程池的优点：
 * 1。节约资源开销
 * 2。相应速度快
 * 3。方便管理线程
 * @Author sugar
 * @Date 2021/8/7 11:03 AM
 * @Version 1.0
 */
public class ThreadPoolDemo1 {

    public static void main(String[] args) {
        /**
         * Executors 创建线程池的三大方法
         */

//        ExecutorService threadPool = Executors.newSingleThreadExecutor();//单个线程
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);//创建一个固定线程池大小的线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        CountDownLatch count = new CountDownLatch(20);


        try {
            for (int i = 0; i < 20; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "   ok");
                    count.countDown();
                });

            }
            count.await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

        System.out.println("hei");


    }
}
