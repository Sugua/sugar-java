package com.sugar.study.collection;

import java.util.Arrays;

public class ArraycopyTest {
    public static void main(String[] args) {
        int[] a = new int[10];

        for (int i = 0; i <3; i++) {
            a[i] = i+1;
        }
        System.arraycopy(a,0,a,7,3);
        for (int i = 0; i < a.length; i++) {
            System.out.printf(a[i]+"\t");
        }

        Arrays.copyOf(a,20);
        for (int i = 0; i < a.length; i++) {
            System.out.printf(a[i]+"\t");
        }
    }
}
