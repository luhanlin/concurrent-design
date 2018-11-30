package com.luhanlin.concurrentdesign.two_phase_termination;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/30 10:10 AM
 */
public class MyTask implements Runnable {

    private final int PHASE = 5;
    private final CyclicBarrier cyclicBarrier;
    private final CountDownLatch countDownLatch;
    private final int count;
    private final Random random = new Random(314159);

    public MyTask(CyclicBarrier cyclicBarrier,CountDownLatch countDownLatch, int count) {
        this.cyclicBarrier = cyclicBarrier;
        this.countDownLatch = countDownLatch;
        this.count = count;
    }

    @Override
    public void run() {

        try {
            for (int i = 0; i < PHASE; i++) {
                doPhase();
                cyclicBarrier.await();
            }
        } catch (BrokenBarrierException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " MyTask count down  " + count);
        }

    }

    private void doPhase(){
        System.out.println(Thread.currentThread().getName() + " MyTask Begin " + count);

        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {

        } finally {
            System.out.println(Thread.currentThread().getName() + " MyTask end " + count);
        }
    }
}
