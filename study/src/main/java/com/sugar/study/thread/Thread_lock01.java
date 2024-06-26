package com.sugar.study.thread;

/**
 * @Description TODO
 * @Author sugar
 * @Date 2021/3/30 11:02 AM
 * @Version 1.0
 */
public class Thread_lock01 {

    private static int m = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[1000];


        for (int i=0;i<1000;i++) {

            threads[i] = new Thread(() -> {

                synchronized (Thread.class){

                    for (int j=0;j<100;j++) {
                        m++;

                    }
                }
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
