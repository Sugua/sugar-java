package com.sugar.study.stream;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @Description 3, 6, 9 等程序员
 * 时间比对
 * @Author sugar
 * @Date 2021/8/7 7:12 PM
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
//        test1();//7373
//        test2();//7385
        test3();//644


    }

    public static void test1() {
        Long sum = 0l;
        long start = System.currentTimeMillis();
        for (Long i = 0l; i <= 1_000_000_000; i++) {
            sum += i;

        }
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + ",耗时:" + (end - start));
    }

    public static void test2() {
        Long sum = 0l;
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> forkJoinTask = new ForkJoinDemo(0L, 1_000_000_000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(forkJoinTask);
        try {
            sum = submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + ",耗时:" + (end - start));


    }

    public static void test3() {
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0L, 1_000_000_000L).reduce(0, Long::sum);

        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + ",耗时:" + (end - start));
    }

}
