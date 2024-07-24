package com.sugar.study.collection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Description todo
 * @Author sugar
 * @Date 2023/7/11 4:37 PM
 * @Version 1.0
 */
public class MapTest {

    public static void main(String[] args) {


        Integer cap = 8;


        List<Integer> integers = Arrays.asList(1, 2, 3);
        System.out.println(integers);
//        integers.add(4);
//1100011
// 110001
//1 1 1 0 0 1 1
        int n = cap - 1;
        System.out.println(n + ": " + Integer.toBinaryString(n));

        System.out.println((n >>> 1) + ":  " + Integer.toBinaryString(n >>> 1));
        n |= n >>> 1 ;
        System.out.println(n + ": " + Integer.toBinaryString(n));

        n |= n >>> 2;
        System.out.println(n + ": " + Integer.toBinaryString(n));

        n |= n >>> 4;
        System.out.println(n + ": " + Integer.toBinaryString(n));

        n |= n >>> 8;
        System.out.println(n + ": " + Integer.toBinaryString(n));

        n |= n >>> 16;
        System.out.println(n + ": " + Integer.toBinaryString(n));
//        map的初始容量大小

        //容量变成2的n次方的数方便移动
        //休息两天

        //tableSizeFor


//        tab[i = (n - 1) & hash])

        long i = 1721814967658L & 99999999;
        System.out.println(i);
    }
}
