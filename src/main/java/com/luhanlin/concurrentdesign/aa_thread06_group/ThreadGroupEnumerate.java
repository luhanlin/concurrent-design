package com.luhanlin.concurrentdesign.aa_thread06_group;

import java.util.concurrent.TimeUnit;

public class ThreadGroupEnumerate {

    public static void main(String[] args) {
        ThreadGroup myThreadGroup = new ThreadGroup("MyThreadGroup");

        new Thread(myThreadGroup, () -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();

        Thread[] list = new Thread[threadGroup.activeCount()];

        // 递归复制active线程
        System.out.println(threadGroup.enumerate(list));
        System.out.println(threadGroup.enumerate(list, false));

    }
}
