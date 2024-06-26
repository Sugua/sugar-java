package com.sugar.study.thread.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 制定通知线程
 * @Author sugar
 * @Date 2021/8/4 11:07 AM
 * @Version 1.0
 */
public class TestCondition {

    public static void main(String[] args){
        Bean bean = new Bean();
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                bean.printA();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                bean.printB();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                bean.printC();
            }
        },"C").start();

    }


}

class Bean{
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();
    private int number = 1;

    public void printA(){
        lock.lock();

        try {
            while (number != 1) {
                conditionA.await();
            }
            System.out.println(Thread.currentThread().getName()+"->AAAA");
            number++;
            conditionB.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB(){
        lock.lock();

        try {
            while (number != 2) {
                conditionB.await();
            }
            System.out.println(Thread.currentThread().getName()+"->BBBB");
            number++;
            conditionC.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void printC(){
        lock.lock();

        try {
            while (number != 3) {
                conditionC.await();
            }
            System.out.println(Thread.currentThread().getName()+"->CCCC");
            number = 1;
            conditionA.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}
