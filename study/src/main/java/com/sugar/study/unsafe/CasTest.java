package com.sugar.study.unsafe;

import sun.misc.Unsafe;

/**
 * @Description todo
 * @Author sugar
 * @Date 2024/6/25 10:16 PM
 * @Version 1.0
 */
public class CasTest {
    private volatile int a ;
    public static void main(String[] args) {
        CasTest casTest = new CasTest();
        new Thread(()->{
            for (int i = 1; i < 5; i++) {
                try {
                    casTest.increment(i);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                System.out.print(casTest.a+"    ");
            }
        }).start();
        new Thread(()->{
            for (int i = 5; i < 10; i++) {
                try {
                    casTest.increment(i);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                System.out.print(casTest.a+"    ");
            }
        }).start();

    }

    private void increment(int i) throws NoSuchFieldException {
        Unsafe unsafe = UnsafeMain.reflectGetUnsafe();
        while (true){
            long fieldOffset = unsafe.objectFieldOffset(CasTest.class.getDeclaredField("a"));

            if (unsafe.compareAndSwapInt(this, fieldOffset, i-1,i )) break;

        }
    }
}
