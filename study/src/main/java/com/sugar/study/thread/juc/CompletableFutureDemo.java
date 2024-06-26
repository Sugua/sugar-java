package com.sugar.study.thread.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Description CompletableFuture demo
 * @Author sugar
 * @Date 2021/8/7 8:22 PM
 * @Version 1.0
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        //没有返回值
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "runAsync");
        });
        try {
            completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "->+++++++++++++++");

        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "->supplyAsync");
            int i = 1 / 0;
            return 1024;
        });
        try {
            System.out.println(completableFuture1.whenComplete((t, u) -> {
                System.out.println("t=>" + t);
                System.out.println("u=>" + u);
            }).exceptionally(e -> {
                e.printStackTrace();
                return 123;
            }).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
