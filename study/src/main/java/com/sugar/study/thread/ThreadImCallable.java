package com.sugar.study.thread;

import java.util.concurrent.*;

/**
 * @Description TODO
 * @Author sugar
 * @Date 2021/8/1 9:46 PM
 * @Version 1.0
 */
public class ThreadImCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {

        System.out.println(11);
        return 100;
    }
}




class Test{

    public static void main(String[] args){
        FutureTask<Integer> futureTask = new FutureTask(new ThreadImCallable());

        new Thread(futureTask).start();
        try {
            Integer integer = futureTask.get();
            System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



//        thread pool


        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<Integer> r1 = executorService.submit(new ThreadImCallable());
        try {
            System.out.println(r1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
