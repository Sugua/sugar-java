package com.sugar.study.thread.gaoji;

/**
 * @Description 生产者消费者
 * @Author sugar
 * @Date 2021/8/1 5:46 PM
 * @Version 1.0
 */
public class TestPc {

   public static void main(String[] args){
       Container container = new Container();

       Producer producer = new Producer(container);
       Consumer consumer = new Consumer(container);

       new Thread(producer,"producer1").start();
       new Thread(producer,"producer2").start();
//       new Thread(consumer,"consumer1").start();
       new Thread(consumer,"consumer2").start();
//       int[] ints = new int[10];
//       ints[9]=10;
//       System.out.println(ints.length);
   }
}
