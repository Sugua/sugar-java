package com.sugar.study.lambda;

import java.util.Comparator;

/**
 * @Description Comparator 是老Java中的经典接口， Java 8在此之上添加了多种默认方法：
 * @Author sugar
 * @Date 2022/4/29 11:25 AM
 * @Version 1.0
 */
public class ComparatorDemo {
    public static void main(String[] args){

        Comparator<String> comparator = (s1, s2) -> s1.compareTo(s2);
        String s1 = "202201";
        String s2 = "202202";
        System.out.println(comparator.compare(s1, s2));

    }
}
