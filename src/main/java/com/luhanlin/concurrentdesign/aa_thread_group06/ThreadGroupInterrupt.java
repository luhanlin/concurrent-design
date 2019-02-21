package com.luhanlin.concurrentdesign.aa_thread_group06;

import java.util.concurrent.TimeUnit;

/**
 * @description: 线程组打断
 * @author: Mr.Lu
 * @create: 2019-02-19 14:54
 **/
public class ThreadGroupInterrupt {

    public static void main(String[] args) throws InterruptedException {

        ThreadGroup group01 = new ThreadGroup("group01");

        new Thread(group01, () -> {
            while (true){
                try {
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("t1 is interrupted");
                    break;
                }
            }
        }, "t1").start();
        new Thread(group01, () -> {
            while (true){
                try {
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("t2 is interrupted");
                    break;
                }
            }
        }, "t2").start();

        TimeUnit.SECONDS.sleep(2);
        group01.interrupt();

    }
}
