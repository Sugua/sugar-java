package com.sugar.study.thread.gaoji;

/**
 * @Description 演员
 * @Author sugar
 * @Date 2021/8/1 6:44 PM
 * @Version 1.0
 */
public class Player implements Runnable {
    TV tv = new TV();

    public Player(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {

            if (i % 2 == 0) {

                tv.play("《快乐大本营》");
            }else {
                tv.play("抖音：记录美好生活");
            }
        }

    }
}
