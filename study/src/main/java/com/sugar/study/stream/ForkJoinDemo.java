package com.sugar.study.stream;

import java.util.concurrent.RecursiveTask;

/**
 * @Description ForkJoin 就像是大数据里的map reduce 思想， 把任务分为很多小任务，然后在汇总
 * @Author sugar
 * @Date 2021/8/7 3:39 PM
 * @Version 1.0
 */
public class ForkJoinDemo extends RecursiveTask<Long> {
    private Long start;
    private Long end;
    private Long temp = 10000l;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected Long compute() {
        if (end - start > temp) {
            Long sum = 0l;
            for (Long i = start; i <= end; i++) {
                sum += i;

            }
            return sum;

        } else {

            long middle = (start + end) / 2;//中间值
            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
            task1.fork();//拆分任务，把任务压入线程队列中
            ForkJoinDemo task2 = new ForkJoinDemo(middle + 1, end);
            task2.fork();
            return task1.join() + task2.join();


        }
    }
}
