package com.sugar.study.cas;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description CAS compare and set
 * cas 优点
 * 内存操作，效率很高
 * 缺点
 * 1。循环会耗时，（while）
 * 2.一次只能保证一个共享变量的原子性
 * 3。ABA问题
 * @Author sugar
 * @Date 2021/8/7 10:22 PM
 * @Version 1.0
 */
public class CasDemo {
    public static void main(String[] args) {
        AtomicInteger num = new AtomicInteger(2020);


        //期望， 更新
        //// 如果我期望的值达到了，那么就更新，否则，就不更新, CAS 是CPU的并发原语！

//        System.out.println(num.compareAndSet(2020, 2021));
//        System.out.println(num.get());
//        num.getAndIncrement();
//
//        System.out.println(num.compareAndSet(2020, 2021));
//        System.out.println(num.get());


        String str = "                    @                   ";
        System.out.println(str);
        System.out.println(str.replaceAll(" +@ +", "@"));

        List<String> list = Arrays.asList("haha", "嘿嘿");

        String[] strings = list.toArray(new String[5]);

        for (String string : strings) {
            System.out.println(string);
        }


    }
}
