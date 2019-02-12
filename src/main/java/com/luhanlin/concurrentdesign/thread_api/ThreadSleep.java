package com.luhanlin.concurrentdesign.thread_api;

import java.util.concurrent.TimeUnit;

public class ThreadSleep {

    public static void main(String[] args) {

        /** 测试线程和主线程互不影响 */
        new Thread(() -> {
            long startTime = System.currentTimeMillis();
            mySleep(2);
            long endTime = System.currentTimeMillis();

            System.out.println(String.format("Total spend %d ms", endTime - startTime));
        }).start();

        long startTime = System.currentTimeMillis();
        mySleep(3);
        long endTime = System.currentTimeMillis();

        System.out.println(String.format("Main thread spend %d ms", endTime - startTime));
    }

    public static void mySleep(long second){
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
