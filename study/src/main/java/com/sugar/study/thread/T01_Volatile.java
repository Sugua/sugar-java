package com.sugar.study.thread;

import java.util.concurrent.TimeUnit;

/**
 * @Description volatile
 * @Author sugar
 * @Date 2021/3/18 2:36 PM
 * @Version 1.0
 */
public class T01_Volatile {
    volatile boolean flag = true;

    void m(){
        System.out.println("m -> start");
        while (flag) {

        }

    }
    public static void main(String[] args){
        T01_Volatile t = new T01_Volatile();

        new Thread(t::m,"t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        t.flag = false;


    }
}
