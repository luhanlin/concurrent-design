package com.luhanlin.concurrentdesign.two_phase_termination;

import java.io.OutputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/29 3:48 PM
 */
public class Main {

    private static int TASKS = 3;

    public static void main(String[] args) {
//        CountUpThread countUpThread = new CountUpThread();
//        countUpThread.start();
//
//        try {
//            Thread.sleep(10000);
//
//            countUpThread.shutdownRequest();
//
//            countUpThread.join();
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println(" CountDownLatch begin ");

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(TASKS, () -> System.out.println(" Barrier action "));
        CountDownLatch countDownLatch = new CountDownLatch(TASKS);

        try {
            for (int i = 0; i < TASKS; i++) {
                executorService.execute(new MyTask(cyclicBarrier, countDownLatch, i));
            }

            System.out.println(" CountDownLatch await ");

            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
            System.out.println(" CountDownLatch end");
        }

    }
}
