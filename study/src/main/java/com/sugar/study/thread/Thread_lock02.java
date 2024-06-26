package com.sugar.study.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description TODO
 * @Author sugar
 * @Date 2021/3/30 11:02 AM
 * @Version 1.0
 */
public class Thread_lock02 {

    private static int m = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[1000];
        ReentrantLock lock = new ReentrantLock();


        for (int i=0;i<1000;i++) {


            threads[i] = new Thread(() -> {

                lock.lock();

                    for (int j=0;j<100;j++) {
                        m++;

                    }
                lock.unlock();


            });
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread t : threads) {

            t.join();

        }


        System.out.println(m);
    }
}
