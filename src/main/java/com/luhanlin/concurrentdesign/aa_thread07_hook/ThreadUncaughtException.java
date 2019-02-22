package com.luhanlin.concurrentdesign.aa_thread07_hook;

import java.util.concurrent.TimeUnit;

/**
 * @description: 线程受检异常捕获
 * @author: Mr.Lu
 * @create: 2019-02-20 09:47
 **/
public class ThreadUncaughtException {

    public static void main(String[] args) {
        // 设值全局回调接口
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println(t.getName() + " occur exception . ");
            e.printStackTrace();
        });

        new Thread(() -> {
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 制造异常
                System.out.println(1 / 0);
            }
        },"Test Thread").start();

    }
}
