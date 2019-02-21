package com.luhanlin.concurrentdesign.aa_thread_api03;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupt {

    public static void main(String[] args) {

        // 判断当前线程是否中断 使用interrupted（）
        System.out.println("Main is interrupted? " + Thread.interrupted());

        // 中断当前线程
        Thread.currentThread().interrupt();

        // 判断当前线程是否中断 isInterrupted（）
        System.out.println("Main is interrupted? " + Thread.currentThread().isInterrupted());

        try {
            TimeUnit.MINUTES.sleep(1L);
        } catch (InterruptedException e) {
            System.out.println("I will be interrupted still.");
        }
    }
}
