package com.sugar.study.thread.juc.threadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @Description todo
 * @Author sugar
 * @Date 2022/7/12 5:10 PM
 * @Version 1.0
 */
public class CoreThreadPool implements Executor {
    private BlockingQueue<Runnable> workQueue;
    private static final AtomicInteger COUNTER = new AtomicInteger();

    private int coreSize;
    private int threadCount = 0;

    public CoreThreadPool(int coreSize) {
        this.coreSize = coreSize;
        this.workQueue = new LinkedBlockingDeque<>();
    }


    @Override
    public void execute(Runnable command) {

        if (++threadCount < coreSize) {
            new Worker(command).start();
        } else {
            try {
                workQueue.put(command);
            } catch (InterruptedException e) {

                throw new IllegalStateException(e);
            }
        }

    }

    private class Worker extends Thread {
        private Runnable firstTask;

        public Worker(Runnable runnable) {
            super(String.format("Worker-%d", COUNTER.getAndIncrement()));
            this.firstTask = runnable;
        }

        @Override
        public void run() {

            Runnable task = this.firstTask;
            while (null != task || null != (task = getTask())) {
                try {
                    task.run();

                } finally {
                    task = null;
                }
            }
            super.run();
        }

        private Runnable getTask() {
            try {
                return workQueue.take();
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        CoreThreadPool pool = new CoreThreadPool(5);

//        new ThreadPoolExecutor()

        IntStream.range(0, 10).forEach(i -> pool.execute(() -> System.out.println(String.format("Thread:%s,value:%d", Thread.currentThread().getName(), i))));
        Thread.sleep(Integer.MAX_VALUE);


    }
}


