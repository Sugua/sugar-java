package com.sugar.study.thread.juc.lock;


import java.util.concurrent.TimeUnit;

/**
 * @Description 八锁现象理解锁
 * @Author sugar
 * @Date 2021/8/4 4:59 PM
 * @Version 1.0
 */
public class Test2 {
    public static void main(String[] args){
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();

        new Thread(()->phone1.sendSMS()).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->phone2.call()).start();

    }

}

class Phone2{

    public static synchronized void sendSMS(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发送短信");


    }


    public static synchronized void call(){
        System.out.println("打电话");
    }
}
