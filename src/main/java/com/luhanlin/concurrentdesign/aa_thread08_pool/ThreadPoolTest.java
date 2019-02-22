package com.luhanlin.concurrentdesign.aa_thread08_pool;

import java.util.concurrent.TimeUnit;

/**
 * @description: 自定义线程池测试
 * @author: Mr.Lu
 * @create: 2019-02-21 14:47
 **/
public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        final ThreadPool threadPool = new BasicThreadPool(2, 6, 4, 18);

        for (int i = 0; i < 20; i++) {
            threadPool.execute(() ->{
                try {
                    System.out.println(Thread.currentThread().getName() + " is running");
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " is done.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

//        while (true){
//            System.out.println("getActiveCount: " + threadPool.getActiveCount());
//            System.out.println("getCoreSize: " + threadPool.getCoreSize());
//            System.out.println("getInitSize: " + threadPool.getInitSize());
//            System.out.println("getMaxSize: " + threadPool.getMaxSize());
//            System.out.println("getQueueSize: " + threadPool.getQueueSize());
//            System.out.println("-----------------------------------");
//            TimeUnit.SECONDS.sleep(5);
//        }

        //  测试线程池销毁
        TimeUnit.SECONDS.sleep(12);

        threadPool.shutdown();

        Thread.currentThread().join();

    }
}
