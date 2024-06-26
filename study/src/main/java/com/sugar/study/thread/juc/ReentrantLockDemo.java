package com.sugar.study.thread.juc;

/**
 * @Description 可重入锁 synchronized版本
 * @Author sugar
 * @Date 2021/8/7 11:14 PM
 * @Version 1.0
 */
public class ReentrantLockDemo {


    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> phone.sms(), "A").start();
        new Thread(()->phone.sms(),"B").start();

    }


}


class Phone {
    public synchronized void sms() {
        System.out.println(Thread.currentThread().getName() + "->sms");
        call();

    }

    public synchronized void call() {

        System.out.println(Thread.currentThread().getName() + "->call");

    }
}