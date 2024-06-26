package com.sugar.study.thread.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 自旋锁 自定义一个自旋锁
 * @Author sugar
 * @Date 2021/8/7 11:25 PM
 * @Version 1.0
 */
public class SpinlockDemo {

    public static void main(String[] args){
//        Lock lock = new ReentrantLock();
//        lock.lock();
//        lock.unlock();
        MyLock lock = new MyLock();

        new Thread(()->{

            lock.myLock();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.myUnLock();
            }

        },"T1").start();


//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        new Thread(()->{

            lock.myLock();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.myUnLock();
            }

        },"T2").start();





    }
}

class  MyLock{
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void  myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"->my lock");
        while (!atomicReference.compareAndSet(null,thread)){}
    }

    public void myUnLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"->my unLock ");
        atomicReference.compareAndSet(thread, null);
    }


}