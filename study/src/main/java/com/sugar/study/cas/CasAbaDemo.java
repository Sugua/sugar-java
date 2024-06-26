package com.sugar.study.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Description 解决ABA问题
 * 解决ABA 问题，引入原子引用！ 对应的思想：乐观锁！
 * @Author sugar
 * @Date 2021/8/7 10:54 PM
 * @Version 1.0
 */
public class CasAbaDemo {
    //AtomicStampedReference 注意，如果范性是包装类， 注意对象的引用类型
    // 正常在业务操作，这里面比较的都是一个个对象
    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1, 1);


    public static void main(String[] args) {


        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();

            System.out.println("a1=>" + stamp);


            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            atomicStampedReference.compareAndSet(1, 2,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);


            System.out.println("a2=>" + atomicStampedReference.getStamp());


            System.out.println(atomicStampedReference.compareAndSet(2, 1,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("a3=>"+atomicStampedReference.getStamp());
        },"a").start();


        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println("b1=>" + stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomicStampedReference.compareAndSet(1, 6,
                    stamp, stamp + 1));
            System.out.println("b2=>" + stamp);
        }).start();




    }
}
