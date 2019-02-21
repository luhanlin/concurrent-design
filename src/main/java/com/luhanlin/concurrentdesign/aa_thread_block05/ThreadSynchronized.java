package com.luhanlin.concurrentdesign.aa_thread_block05;

import java.util.concurrent.TimeUnit;

public class ThreadSynchronized {

    public static void main(String[] args) throws InterruptedException {
        ThreadSynchronized sy = new ThreadSynchronized();

        Thread t1 = new Thread(sy::synchronTest, "T1");
        t1.start();
        // 确保 t1 启动成功
        TimeUnit.MILLISECONDS.sleep(2L);

        Thread t2 = new Thread(sy::synchronTest, "T2");
        t2.start();
        // 确保 t2 启动成功
        TimeUnit.MILLISECONDS.sleep(2L);

        // 测试打断
        t2.interrupt();

        System.out.println("t2 isInterrupted ? " + t2.isInterrupted()); // true
        System.out.println("t2 State is " + t2.getState());  // BLOCKED

        /**
         * result :
         *  T1 enter the method
         *  t2 isInterrupted ? true
         *  t2 State is BLOCKED
         *  T2 enter the method
         *  java.lang.InterruptedException: sleep interrupted
         */
    }


    public synchronized void synchronTest(){
        try {
            System.out.println(Thread.currentThread().getName() + " enter the method");
            TimeUnit.SECONDS.sleep(2L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
