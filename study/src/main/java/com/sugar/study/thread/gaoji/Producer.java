package com.sugar.study.thread.gaoji;

/**
 * @Description 生产着
 * @Author sugar
 * @Date 2021/8/1 5:53 PM
 * @Version 1.0
 */
public class Producer implements Runnable {
    private Container container;

    public Producer(Container container) {

        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            container.production(new Chicken(i));
            System.out.println("生产了" + i + "只鸡");
        }

    }
}
