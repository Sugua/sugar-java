package com.sugar.study.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * @Description todo
 * @Author sugar
 * @Date 2023/2/12 2:38 PM
 * @Version 1.0
 */
public class LockSuportTest {
    List<Object> list = new ArrayList<>();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        LockSuportTest l = new LockSuportTest();
        t1=new Thread( ()->{
            System.out.println(Thread.currentThread().getName() + "启动");
            LockSupport.park();
            LockSupport.unpark(t2);
            System.out.println(Thread.currentThread().getName() + "结束");


        },"t1");
        t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "启动");
            for (int i = 0; i < 10; i++) {

                l.add(new Object());
                System.out.println(i);
                if (l.size() == 5) {

                    LockSupport.unpark(t1);
                    LockSupport.park();
                }
            }
            System.out.println(Thread.currentThread().getName() + "结束");



        }, "t2");
        t1.start();
        t2.start();

    }


}
