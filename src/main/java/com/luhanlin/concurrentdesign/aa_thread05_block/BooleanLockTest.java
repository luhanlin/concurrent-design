package com.luhanlin.concurrentdesign.aa_thread05_block;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static java.lang.Thread.currentThread;

public class BooleanLockTest {

    private final Lock lock = new BooleanLock();

    /**
     * 测试 自定义显示锁
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        BooleanLockTest booleanLocktest = new BooleanLockTest();

        // test01
//        IntStream.range(1,10)
//                .mapToObj(i -> new Thread(booleanLocktest::sysMethod))
//                .forEach(Thread::start);

        // 测试锁被打断
//        Thread t1 = new Thread(booleanLocktest::sysMethod, "T1");
//        t1.start();
//
//        TimeUnit.MILLISECONDS.sleep(2L);
//
//        Thread t2 = new Thread(booleanLocktest::sysMethod, "T2");
//        t2.start();
//
//        TimeUnit.MILLISECONDS.sleep(10L);
//
//        t2.interrupt();

        // 测试超时
        Thread t1 = new Thread(booleanLocktest::timeOutMethod, "T1");
        t1.start();

        TimeUnit.MILLISECONDS.sleep(2L);

        Thread t2 = new Thread(booleanLocktest::timeOutMethod, "T2");
        t2.start();

        TimeUnit.MILLISECONDS.sleep(10L);

    }

    private void sysMethod() {
        try {
            lock.lock();
            int i = ThreadLocalRandom.current().nextInt(10);
            System.out.println(currentThread() + " get the lock .");
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void timeOutMethod() {
        try {
            lock.lock(1000L);
            int i = ThreadLocalRandom.current().nextInt(10);
            System.out.println(currentThread() + " get the lock .");
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
