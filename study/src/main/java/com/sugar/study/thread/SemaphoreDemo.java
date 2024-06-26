package com.sugar.study.thread;


import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Description Semaphore demo 信号量
 * 作用： 多个共享资源互斥的使用！并发限流，控制最大的线程数！
 * @Author sugar
 * @Date 2021/8/6 5:38 PM
 * @Version 1.0
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(30);
        for (int i = 0; i < 500; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();//获得，假设如果已经满了，等待，等待被释放为止！
                    System.out.println(Thread.currentThread().getName() + "->抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "->驶出车库");


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();//释放，会将当前的信号量释放 + 1，然后唤醒等待的线程！
                }

            }, String.valueOf(i)).start();


        }

    }
}
