package com.luhanlin.concurrentdesign.thread_hook07;

import java.util.concurrent.TimeUnit;

/**
 * @description: 钩子线程
 * @author: Mr.Lu
 * @create: 2019-02-20 10:28
 **/
public class ThreadHook {

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                System.out.println("Hook 1 thread is running.");
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hook 1 thread will exit.");
        }, "hook 1"));

        // 注册多个钩子函数
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                System.out.println("Hook 2 thread is running.");
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hook 2 thread will exit.");
        }, "hook 2"));

        // 添加一个阻塞线程
        new Thread(() -> {
            try {
                System.out.println("Un daemon thread is running.");
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Un daemon thread will exit.");
        }, "Un daemon thread").start();

        System.out.println(" jvm will stop.");
    }
}
