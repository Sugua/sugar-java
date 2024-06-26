package com.sugar.study.jmm;

import java.util.concurrent.TimeUnit;

/**
 * @Description volatile 三大特性
 * 1。保证可见性
 * 2。不保证原子性
 * 3。禁止指令重拍（内存屏障）
 * jmm 约定
 * 1。线程释放锁之前，必须把共享变量立刻刷回主存
 * 2。线程加锁之前，必须读取主存的最新值到内存中
 * 3。加锁和解锁是同一把锁
 * @Author sugar
 * @Date 2021/8/7 9:29 PM
 * @Version 1.0
 */
public class VolatileDemo1 {
    //保证可见性
    private  static volatile int num = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            while (num == 0) {

            }
        }).start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num = 1;
        System.out.println(num);

    }
}
