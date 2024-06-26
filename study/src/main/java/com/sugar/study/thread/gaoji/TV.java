package com.sugar.study.thread.gaoji;

/**
 * @Description TODO
 * @Author sugar
 * @Date 2021/8/1 9:24 PM
 * @Version 1.0
 */
public class TV {
    String voice;
    boolean flag;


    public synchronized void play(String voice) {

        if (flag) {

            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("演员表演了：" + voice);
        this.notifyAll();
        this.voice = voice;
        this.flag=!flag;

    }

    public synchronized void watch() {

        if(!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("观众观看了：" + voice);
        this.notifyAll();
        this.flag = !flag;
    }
}
