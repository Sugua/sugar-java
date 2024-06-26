package com.sugar.study.thread;

/**
 * @Description CPU乱序
 * @Author sugar
 * @Date 2021/3/18 3:03 PM
 * @Version 1.0
 */
public class T01_CPU {
    private static int x = 0, y = 0, a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {

        int i = 0;
        for (;;) {

            x = 0; y = 0;
            a = 0; b = 0;

            i++;
            Thread one = new Thread(() -> {
                shotWait(100000);
                a = 1;
                x = b;
//                System.out.print(" x = "+x);
            });

            Thread other = new Thread(() -> {
                b = 1;
                y = a;
//                System.out.println(" y = "+y);

            });


            one.start();
            other.start();
            one.join();
            other.join();
            String result = "第" + i + "次(" + x + "," + y + ")";
            if (x == 0 && y == 0) {

                System.out.println(result);
                break;
            }else {
//                System.out.println(result);

            }
        }
    }

    private static void shotWait(long interval) {
        long start = System.nanoTime();
        long end;
        do {
            end = System.nanoTime();
        } while (start + interval >= end );
    }

}
