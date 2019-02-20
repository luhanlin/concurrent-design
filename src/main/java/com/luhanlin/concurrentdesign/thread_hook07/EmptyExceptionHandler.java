package com.luhanlin.concurrentdesign.thread_hook07;

import java.util.concurrent.TimeUnit;

/**
 * @description: 无接口定义抛出异常
 * @author: Mr.Lu
 * @create: 2019-02-20 10:07
 **/
public class EmptyExceptionHandler {

    public static void main(String[] args) {
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();

        System.out.println(threadGroup.getName());
        System.out.println(threadGroup.getParent());
        System.out.println(threadGroup.getParent().getParent());

        new Thread(() -> {
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(2L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 制造异常
                System.out.println(1 / 0);
            }
        }, "empty thread").start();
    }
}
