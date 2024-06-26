package com.sugar.study.thread.juc.threadPool;


import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * @Description todo
 * @Author sugar
 * @Date 2022/7/13 2:17 PM
 * @Version 1.0
 */
public class Test {


    public static void main(String[] args) {


        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        Set<Integer> set = new HashSet<>();
        System.out.println(set.add(1));
        System.out.println(set.add(2));
        System.out.println(set.add(1));
        System.out.println(set);
    }

    static void foo() {
    }
}
