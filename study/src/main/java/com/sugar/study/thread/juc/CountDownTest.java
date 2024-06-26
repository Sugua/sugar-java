package com.sugar.study.thread.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Description todo
 * @Author sugar
 * @Date 2023/2/12 2:18 PM
 * @Version 1.0
 */
public class CountDownTest {
    private List<Object> list = new ArrayList<>();

    public void add(Object o){
        list.add(o);
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args){
        CountDownLatch latch = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);
        CountDownTest c = new CountDownTest();


        Thread t = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"启动");
            if (c.size() != 5) {

                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2结束");
            latch2.countDown();
        },"T1");

        t.start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "启动");
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println(i);

                if (c.size() == 5) {
                    latch.countDown();
                    try {
                        latch2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }



        },"T2").start();



    }
}
