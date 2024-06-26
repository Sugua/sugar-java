package com.sugar.study.thread;

import java.nio.ByteBuffer;

/**
 * @Description TODO
 * @Author sugar
 * @Date 2021/3/18 3:44 PM
 * @Version 1.0
 */
public class T {
    public static void main(String[] args){
//
//        ByteBuffer buf = ByteBuffer.allocate(1024);
//
////        System.out.println("初始化");
////        System.out.println("position：" + buf.position());
////        System.out.println("limit：" + buf.limit());
////        System.out.println("capacity：" + buf.capacity());
//
//        String str = "abcde";
//
//        buf.put(str.getBytes());
//
////        System.out.println("存入数据到缓冲区");
////        System.out.println("position：" + buf.position());
////        System.out.println("limit：" + buf.limit());
////        System.out.println("capacity：" + buf.capacity());
//
//        // 切换读取数据模式
//        buf.flip();
////        System.out.println("切换读取数据模式");
////        System.out.println("position：" + buf.position());
////        System.out.println("limit：" + buf.limit());
////        System.out.println("capacity：" + buf.capacity());
//
//        System.out.println("开始读取数据");
//        byte[] dst = new byte[buf.limit()];
//
//        buf.get(dst);
//        System.out.println(new String(dst, 0, dst.length));
//
////        System.out.println("数据读取完毕");
////        System.out.println("position：" + buf.position());
////        System.out.println("limit：" + buf.limit());
////        System.out.println("capacity：" + buf.capacity());
//        buf.rewind();//重复读取
//
//        System.out.println("rewind");
//
//        System.out.println("position: " + buf.position());
//        System.out.println("limit: " + buf.limit());
//        System.out.println("capacity: " + buf.capacity());
//
//        buf.clear();
//
//
//        System.out.println("clear");
//        System.out.println("position: " + buf.position());
//        System.out.println("limit: " + buf.limit());
//        System.out.println("capacity: " + buf.capacity());
//
//        int n = 10000-1;
//
//        n |= n >>> 1;
//        System.out.println(n);
//        n |= n >>> 2;
//        System.out.println(n);
//        n |= n >>> 4;
//        System.out.println(n);
//        n |= n >>> 8;
//        System.out.println(n);
//        n |= n >>> 16;
//        System.out.println(n);
//
//        System.out.println(n+1);

        Long l1 = 1l;
        Long l2 = 1l;
        Long l3 = null;
        Long l4 = new Long(1);
        System.out.println(l1==l2);
        System.out.println(l2==l3);
        System.out.println(l4==l3);

        System.out.println(l2.equals(l4));
        System.out.println(l3.toString());





    }
}
