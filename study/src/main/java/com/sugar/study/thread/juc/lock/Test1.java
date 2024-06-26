package com.sugar.study.thread.juc.lock;


import java.util.concurrent.TimeUnit;

/**
 * @Description 八锁现象理解锁
 * @Author sugar
 * @Date 2021/8/4 4:59 PM
 * @Version 1.0
 */
public class Test1 {
    public static void main(String[] args){
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        new Thread(()->phone1.sendSMS()).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->phone1.call()).start();

    }

}

class Phone{

    public synchronized void sendSMS(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发送短信");


    }


    public synchronized void call(){
        System.out.println("打电话");
    }
}
