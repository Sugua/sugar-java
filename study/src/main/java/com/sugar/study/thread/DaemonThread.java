package com.sugar.study.thread;

/**
 * @Description TODO
 * @Author sugar
 * @Date 2021/8/1 4:32 PM
 * @Version 1.0
 */
public class DaemonThread {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        Thread godThread = new Thread(god);
        godThread.setDaemon(true);
        Thread youThread = new Thread(you);

        godThread.start();
        youThread.start();
    }

}

class God implements Runnable {
    @Override
    public void run() {
        while (true)
        System.out.println("上帝保佑着你");
    }
}

class You implements Runnable {
    private int age = 0;

    @Override
    public void run() {


        while (age <= 100) {
            System.out.println("你已经" + age++ + "岁了");
            if(age>100)
                    System.out.println("死了");
        }
    }
}
