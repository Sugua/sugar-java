package com.sugar.study.thread.juc;

import java.util.concurrent.TimeUnit;

/**
 * @Description 死锁
 * @Author sugar
 * @Date 2021/8/7 11:39 PM
 * @Version 1.0
 */
public class DeadDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new MyThread1(lockA, lockB), "T1").start();
        new Thread(new MyThread1(lockB, lockA), "T2").start();
    }
}

class MyThread1 implements Runnable {
    private String lockA;
    private String lockB;

    public MyThread1(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }


    @Override
    public void run() {
//
//        synchronized (lockA) {
//            System.out.println(Thread.currentThread().getName() + "=>lock:" + lockA + "=>get" + lockB);
//
//            try {
//                TimeUnit.SECONDS.sleep(6);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            synchronized (lockB) {
//                System.out.println(Thread.currentThread().getName() + "=>lock:" + lockB + "=>get" + lockA);
//
//            }
//        }


        synchronized (lockA){

            System.out.println(Thread.currentThread().getName() +
                    "lock:"+lockA+"=>get"+lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() +
                        "lock:"+lockB+"=>get"+lockA);
            }
        }

    }
}