package com.sugar.study.collection;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * @Description todo
 * @Author sugar
 * @Date 2022/3/29 2:36 PM
 * @Version 1.0
 */
public class collectTest {


    public static void main(String[] args) {
        int[] myArray = {1, 2, 3};

        List<int[]> ints = Arrays.asList(myArray);

        System.out.println(ints);//这返回的是数组的地址


        int[] a = new int[10];

        a[0]=1;
        a[1]=2;
        a[2]=3;
        a[3]=4;


        System.arraycopy(a,2,a,3,3);

        a[2]=99;
        for (int i : a) {
            System.out.println(i);
        }


        int[] ints1 = Arrays.copyOf(a, 20);
        System.out.println(ints1.length);

        LocalDate pre = LocalDate.now();

        System.out.println(pre.format(DateTimeFormatter.ofPattern("YYYYMM")));


    }
}
