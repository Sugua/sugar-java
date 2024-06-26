package com.sugar.study.thread.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 可重入锁 Lock 版本
 * @Author sugar
 * @Date 2021/8/7 11:20 PM
 * @Version 1.0
 */
public class ReentrantLockDemo2 {
    public static void main(String[] args) {

        LockPhone lockPhone = new LockPhone();
        new Thread(()->lockPhone.sms(),"A").start();
        new Thread(()->lockPhone.sms(),"B").start();
    }
}


class LockPhone{

    private Lock lock = new ReentrantLock();


    public void sms(){
        lock.lock();


        try {
            System.out.println(Thread.currentThread().getName() + "->sms");
            call();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void call() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "->call");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}