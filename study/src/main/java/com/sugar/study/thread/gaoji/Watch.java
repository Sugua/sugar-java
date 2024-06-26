package com.sugar.study.thread.gaoji;

/**
 * @Description TODO
 * @Author sugar
 * @Date 2021/8/1 9:36 PM
 * @Version 1.0
 */
public class Watch implements Runnable {
    TV tv ;

    public Watch(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {

            tv.watch();
        }


    }
}
