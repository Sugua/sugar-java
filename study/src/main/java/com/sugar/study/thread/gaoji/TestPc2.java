package com.sugar.study.thread.gaoji;

/**
 * @Description 红绿灯实现消费者问题
 * @Author sugar
 * @Date 2021/8/1 9:23 PM
 * @Version 1.0
 */
public class TestPc2 {

    public static void main(String[] args){
        TV tv = new TV();
        Player player = new Player(tv);
        Watch watch = new Watch(tv);


        new Thread(player).start();
        new Thread(watch).start();




    }
}
