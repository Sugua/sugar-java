package com.sugar.study.thread.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description 读写锁
 * @Author sugar
 * @Date 2021/8/6 10:52 PM
 * @Version 1.0
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache cache = new MyCache();

        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> cache.put(temp + "", temp), String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {

            final int temp = i;
            new Thread(() -> cache.get(temp + ""), String.valueOf(i)).start();
        }

    }


}

class MyCache {


    private volatile Map<String, Object> cache = new HashMap();
//    private Lock lock = new ReentrantLock();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public boolean put(String key, Object value) {
        boolean flag = false;
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 开始写入 " + key);
//            TimeUnit.SECONDS.sleep(1);
            cache.put(key, value);
            flag = true;
            System.out.println(Thread.currentThread().getName() + " 写入ok");

        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
            return flag;
        }

    }

    public Object get(String key) {
        lock.readLock().lock();
        Object o = null;
        try {
            System.out.println(Thread.currentThread().getName() + " 开始读取->" + key);

            o = cache.get(key);
            System.out.println(Thread.currentThread().getName() + " 读取ok->" + o);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
            return o;
        }

    }


}