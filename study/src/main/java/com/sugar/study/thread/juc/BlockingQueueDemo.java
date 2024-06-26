package com.sugar.study.thread.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description 阻塞队列
 * （会抛出异常）
 * （不回异常）
 * （阻塞等待）
 * （超时等待）
 * @Author sugar
 * @Date 2021/8/6 11:20 PM
 * @Version 1.0
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> addOrRemove = new ArrayBlockingQueue<>(3);
        //1.会出现异常
        System.out.println(addOrRemove.add("a"));
        System.out.println(addOrRemove.add("b"));
        System.out.println(addOrRemove.add("c"));
//        System.out.println(blockingQueue.add("d"));//超出长度回出现异常

        System.out.println("==========");

        System.out.println(addOrRemove.remove());
        System.out.println(addOrRemove.remove());
        System.out.println(addOrRemove.remove());
//        System.out.println(addOrRemove.remove());//队列位空会出现异常

        //2不会出现异常
//        offer 添加
//        poll 取出
        System.out.println("==========");

        System.out.println("不会出现异常哦");
        System.out.println("==========");

        BlockingQueue<String> offerOrPoll = new ArrayBlockingQueue<>(3);
        System.out.println(offerOrPoll.offer("1"));
        System.out.println(offerOrPoll.offer("2"));
        System.out.println(offerOrPoll.offer("3"));
        System.out.println(offerOrPoll.offer("4"));//不会出现异常， 不会阻塞， 直接返回false

        System.out.println("==========");

        System.out.println(offerOrPoll.poll());
        System.out.println(offerOrPoll.poll());
        System.out.println(offerOrPoll.poll());
        System.out.println(offerOrPoll.poll());//不会出现异常， 不会阻塞， 直接返回null

        System.out.println("==========");

        System.out.println("阻塞的情况我们继续看看,存入队列没有返回参数");
        System.out.println("==========");

        BlockingQueue<String> putOrTake = new ArrayBlockingQueue<>(3);
        try {
            putOrTake.put("1");
            putOrTake.put("2");
            putOrTake.put("3");
//            putOrTake.put("4");//一直阻塞着，等待队列有位置
            System.out.println(putOrTake.take());
            putOrTake.put("4");//一直阻塞着，等待队列有位置
            System.out.println(putOrTake.take());
            System.out.println(putOrTake.take());
            System.out.println(putOrTake.take());
//            System.out.println(putOrTake.take());//一直阻塞着， 直到取到数据

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("==========");

        System.out.println("阻塞的情况我们继续看看，但是这个阻塞有时间设置，只等那么久,有返回参数");
        System.out.println("==========");

        BlockingQueue offerOrPollTime=new ArrayBlockingQueue(3);
        System.out.println(offerOrPollTime.offer("a"));
        System.out.println(offerOrPollTime.offer("b"));
        System.out.println(offerOrPollTime.offer("c"));
        try {
            System.out.println(offerOrPollTime.offer("d",1, TimeUnit.SECONDS));//等待超时后返回false
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(offerOrPollTime.poll());
        System.out.println(offerOrPollTime.poll());
        System.out.println(offerOrPollTime.poll());
        try {
            System.out.println(offerOrPollTime.poll(2,TimeUnit.SECONDS));//等待超时后返回null
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
