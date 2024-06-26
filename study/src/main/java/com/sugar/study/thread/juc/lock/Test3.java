package com.sugar.study.thread.juc.lock;


import java.util.concurrent.TimeUnit;

/**
 * @Description 八锁现象理解锁
 * @Author sugar
 * @Date 2021/8/4 4:59 PM
 * @Version 1.0
 */
public class Test3 {
    public static void main(String[] args){
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();
        Phone3 phone3 = new Phone3();

        new Thread(()->phone1.sendSMS()).start();

//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        new Thread(()->phone2.call()).start();

        new Thread(()->phone3.hello()).start();

    }

}

class Phone3{

    public static synchronized void sendSMS(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发送短信");


    }


    public synchronized void call(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("打电话");
    }

    public  void hello(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello");
    }
}
