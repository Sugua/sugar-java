package com.sugar.study.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description todo
 * @Author sugar
 * @Date 2023/2/14 11:00 AM
 * @Version 1.0
 */
@Slf4j
public class ThreadLocalDemo {

    static ThreadLocal<Person> t1 = new ThreadLocal();
    public static void main(String[] args){
        t1.set(new Person("main"));
        new Thread(()->{
            t1.set(new Person("thread"));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("t ->{}",t1.get());
        }).start();
       log.info("m ->{}",t1.get());
        t1.remove();
        log.info("m remove ->{}",t1.get());


    }

}
class Person {
    String name;

    public Person(String name){
        this.name=name;
    }
    public Person(){}

    @Override
    public String toString() {
        return name;
    }
}