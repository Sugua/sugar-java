package com.sugar.study.thread.gaoji;

/**
 * @Description 消费者
 * @Author sugar
 * @Date 2021/8/1 5:54 PM
 * @Version 1.0
 */
public class Consumer implements Runnable {
    private Container container;

    public Consumer(Container container) {

        this.container = container;
    }


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("吃到了" + this.container.consumption().id + "只鸡");

        }


    }
}
