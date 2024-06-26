package com.sugar.study.thread.gaoji;

/**
 * @Description 容器
 * @Author sugar
 * @Date 2021/8/1 5:55 PM
 * @Version 1.0
 */
public class Container {
    private Chicken chickens[] = new Chicken[10];
    private int count = 0;

    //生产产品
    public synchronized void production(Chicken chicken) {
        while (this.count == this.chickens.length) {
            //通知消费者
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        chickens[count++] = chicken;

        this.notifyAll();
    }

    public synchronized Chicken consumption() {
        while (count == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Chicken chicken = chickens[--count];
        this.notifyAll();

        return chicken;
    }
}
