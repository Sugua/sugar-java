package com.sugar.study.test;

public class Test {
    public static void main(String[] args) {
        String filename="我方开具CN_DN统计表-202405_一卡多号.xlsx";
        String filenamePrefix="一卡多号我方开具CN_DN统计表";
        System.out.println(filename.contains(filenamePrefix));
    }
}
