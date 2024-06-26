package com.sugar.study.thread.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description Callable 测试
 * @Author sugar
 * @Date 2021/8/6 4:26 PM
 * @Version 1.0
 */
public class CallableTset {

    public static void main(String[] args){
        MyThread my = new MyThread();

        FutureTask task = new FutureTask(my);
        new Thread(task).start();

        try {
            System.out.println(task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


}
class MyThread implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        return 1024;
    }
}