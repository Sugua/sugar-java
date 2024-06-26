package com.sugar.study.unsafe;

/**
 * @Description todo
 * @Author sugar
 * @Date 2024/6/20 11:13 PM
 * @Version 1.0
 */
public class ChangeThread implements Runnable {
    /**volatile**/  boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("subThread change flag to:" + flag);
        flag=true;

    }
}
