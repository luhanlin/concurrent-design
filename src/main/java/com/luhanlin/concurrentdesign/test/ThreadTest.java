package com.luhanlin.concurrentdesign.test;


import java.util.concurrent.TimeUnit;

public class ThreadTest {

    public static void main(String[] args) {
//        new Thread(ThreadTest::playMusic).start();
//        playBox();
//        System.out.println(System.getProperties());

        Thread thread = new Thread(ThreadTest::playMusic);
        thread.start();

        sleep(2);

        // 第二次重复启动或者在线程已经 terminated 后，线程不可以再次启动
        thread.start();
    }

    public static void repeat(){
        System.out.println("em repeat ...");
        sleep(1);
    }

    public static void playMusic(){
        for (;  ; ) {
            System.out.println("em playMusic ...");
            sleep(1);
        }
    }

    public static void playBox(){
        for (;  ; ) {
            System.out.println("em playBox ...");
            sleep(1);
        }
    }

    public static void sleep(long second){
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
