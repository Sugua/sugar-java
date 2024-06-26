package com.sugar.study.thread;

/**
 * @Description todo
 * @Author sugar
 * @Date 2023/2/14 11:00 AM
 * @Version 1.0
 */
public class ThreadLocalDemo {

    static ThreadLocal<Person> t1 = new ThreadLocal();
    public static void main(String[] args){
        t1.set(new Person());
        t1.remove();

    }

}
class Person {
    String name;
}