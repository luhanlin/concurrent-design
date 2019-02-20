package com.luhanlin.concurrentdesign.thread_group06;

import java.util.concurrent.TimeUnit;

/**
 * @description: 线程组基本方法测试
 * @author: Mr.Lu
 * @create: 2019-02-19 11:51
 **/
public class ThreadGroupBasic {

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group = new ThreadGroup("group1");

        Thread thread = new Thread(group, () -> {
            while(true){
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread");
        thread.setDaemon(true);
        thread.start();

        TimeUnit.SECONDS.sleep(1L);

        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        System.out.println("activeCount = " + mainGroup.activeCount());
        System.out.println("activeGroupCount = " + mainGroup.activeGroupCount());
        System.out.println("getMaxPriority = " + mainGroup.getMaxPriority());
        System.out.println("getParent = " + mainGroup.getParent());
        System.out.println("getName = " + mainGroup.getName());

        mainGroup.list();

        System.out.println("parentOf = " + mainGroup.parentOf(group));
        System.out.println("parentOf = " + mainGroup.parentOf(mainGroup));

    }

}
