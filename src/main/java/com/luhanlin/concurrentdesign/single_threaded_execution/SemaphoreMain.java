package com.luhanlin.concurrentdesign.single_threaded_execution;

import lombok.extern.log4j.Log4j2;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * 类详细描述：测试信号量 semaphore
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/23 10:32 AM
 */
@Log4j2
public class SemaphoreMain {

    static class BoundedResource{

        private final static Random random = new Random();
        private final Semaphore semaphore;
        private final int permits;

        public BoundedResource(int permits) {
            semaphore = new Semaphore(permits);
            this.permits = permits;
        }

        // do use
        public void use() throws InterruptedException {
            semaphore.acquire();
            try {
                doUse();
            } finally {
                semaphore.release();
            }

        }

        // 真实处理方法
        private void doUse() throws InterruptedException {
            log.info(Thread.currentThread().getName() + ": Begin used [ {} ]",permits - semaphore.availablePermits());
            Thread.sleep(random.nextInt(10000));
            log.info(Thread.currentThread().getName() + ": End used [ {} ]",permits - semaphore.availablePermits());
        }

    }

    static class UserThreadSema extends Thread{

        private final static Random random = new Random();
        private final BoundedResource boundedResource;

        public UserThreadSema(BoundedResource boundedResource) {
            this.boundedResource = boundedResource;
        }

        @Override
        public void run() {
            try {
                boundedResource.use();
                Thread.sleep(random.nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // 一次只能访问三个资源
        BoundedResource boundedResource = new BoundedResource(3);

        // 启动10个线程
        for (int i = 0; i < 10; i++) {

            new UserThreadSema(boundedResource).start();
        }

    }
}
