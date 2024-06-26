package com.sugar.study.thread.juc;

import java.util.concurrent.*;

/**
 * @Description 手动创建一个线程池
 * 7个参数:
 * 4种拒绝策略
 * new ThreadPoolExecutor.AbortPolicy()//超多了最大线程最大承载量（max+queue），不处理，抛出异常
 * new ThreadPoolExecutor.CallRunPolicy()//哪里来的去哪里
 * new ThreadPoolExecutor.DiscardPolicy()//队列满了，丢掉任务，不抛出异常
 * new ThreadPoolExecutor.DiscardOldestPolicy()//队列满了，尝试和最早的竞争，也不会抛出异常
 * @Author sugar
 * @Date 2021/8/7 11:18 AM
 * @Version 1.0
 */
public class ThreadPoolDemo2 {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(Runtime.getRuntime().freeMemory());
        ExecutorService threadPool =new ThreadPoolExecutor(
                2,
                4,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
//        public ThreadPoolExecutor(int corePoolSize,//核心线程池大小
//        int maximumPoolSize,//最大核心线程池大小
//        long keepAliveTime,//超时了没有人调用了就会释放
//        TimeUnit unit,//超时单位
//        BlockingQueue<Runnable> workQueue,//阻塞队列
//        ThreadFactory threadFactory,//线程工厂一般不用动
//        RejectedExecutionHandler handler)//拒绝策略
        try {
            for (int i = 0; i < 30; i++) {
                threadPool.execute(()->System.out.println(Thread.currentThread().getName()+"        ->ok"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }


    }


}
