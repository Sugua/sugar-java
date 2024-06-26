package com.sugar.study.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

/**
 * @Description todo
 * @Author sugar
 * @Date 2024/6/19 11:27 PM
 * @Version 1.0
 */
public class UnsafeMain {
    private int value;


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
//        UnsafeMain unsafeMain = new UnsafeMain();
//        unsafeMain.memoryTest();

        ChangeThread changeThread = new ChangeThread();
        Unsafe unsafe = reflectGetUnsafe();
        new Thread(changeThread).start();

        while (true){
            boolean flag=changeThread.flag;
            unsafe.loadFence();
            if (flag){
                System.out.println("detected flag changed");
                break;
            }
        }

        System.out.println("mian thread is end ");

        System.out.println("********************");
        System.out.println("********************");
        System.out.println("********************");
        System.out.println("********************");
        System.out.println();
        System.out.println();

        assert unsafe!=null;
        long offset = unsafe.objectFieldOffset(UnsafeMain.class.getDeclaredField("value"));
        UnsafeMain main = new UnsafeMain();
        System.out.println("value before putInt: "+main.value );
        unsafe.putInt(main,offset,42);
        System.out.println("value after putInt: "+main.value);
        System.out.println("value after putInt: "+unsafe.getInt(main,offset));

        System.out.println("********************");
        System.out.println("********************");
        System.out.println("********************");
        System.out.println("********************");
        System.out.println();
        System.out.println();

        A a1=new A();
        System.out.println(a1.getB());
        A a2 = A.class.newInstance();
        System.out.println(a2.getB());
        A a3 = (A) unsafe.allocateInstance(A.class);
        System.out.println(a3.getB());


        System.out.println("********************");
        System.out.println("********************");
        System.out.println("********************");
        System.out.println("********************");
        System.out.println();
        System.out.println();


        Thread mainThread = Thread.currentThread();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("subThread try to unpark mainThread ");
                unsafe.unpark(mainThread);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("park main mainThread");
        unsafe.park(false,0L);
        System.out.println("unpark mainThread  success");

        System.out.println("********************");
        System.out.println("********************");
        System.out.println("********************");
        System.out.println("********************");
        System.out.println();
        System.out.println();

        System.out.println(unsafe.addressSize());
        System.out.println(unsafe.pageSize());


    }


    private void memoryTest() {
        Unsafe unsafe = reflectGetUnsafe();

        int size = 4;
        long addr = unsafe.allocateMemory(size);

//        long addr3 = unsafe.reallocateMemory(addr, size * 2);
        long addr3 = unsafe.reallocateMemory(addr, size * 2);

        System.out.println("addr: " + addr);
        System.out.println("addr3: " + addr3);
        System.out.println(addr - addr3);

        try {
            unsafe.setMemory(null, addr, size, (byte) 1);
            for (int i = 0; i < 2; i++) {
//                unsafe.copyMemory(null, addr, null, addr3 + size * i, 4);

                unsafe.copyMemory(null, addr, null, addr3 + size * i, 4);
            }
            System.out.println(unsafe.getInt(addr));
            System.out.println(unsafe.getLong(addr3));
        } finally {
            unsafe.freeMemory(addr);
            unsafe.freeMemory(addr3);

        }

    }

    public static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
