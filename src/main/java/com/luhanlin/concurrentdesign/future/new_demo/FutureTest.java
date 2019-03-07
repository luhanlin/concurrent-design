package com.luhanlin.concurrentdesign.future.new_demo;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @description: 测试future
 * @author: Mr.Lu
 * @create: 2019-03-07 10:22
 **/
public class FutureTest {

    @Test
    public void testFuture() throws InterruptedException {
        FutureService futureService = FutureService.getFutureService();

        Future future = futureService.submit(() -> {

            System.out.println(Thread.currentThread().getName() + " is running.");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
            }
        });

        System.out.println(Thread.currentThread().getName() + " is running.");
        future.get();

        System.out.println(Thread.currentThread().getName() + " is done.");
    }

    @Test
    public void testFuture2() throws InterruptedException {
        FutureService<String, Integer> futureService = FutureService.getFutureService();

        Future<Integer> helloFuture = futureService.submit(input -> {

            System.out.println(Thread.currentThread().getName() + " is running.");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return input.length();
        }, "hello");

        System.out.println(Thread.currentThread().getName() + " is running.");
        Integer result = helloFuture.get();

        System.out.println(Thread.currentThread().getName() + " is done. result = " + result);
    }

    @Test
    public void testFuture3() throws InterruptedException {
        FutureService<String, Integer> futureService = FutureService.getFutureService();

        Future<Integer> helloFuture = futureService.submit(input -> {

            System.out.println(Thread.currentThread().getName() + " is running.");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return input.length();
        }, "hello", System.out::println);

        System.out.println(Thread.currentThread().getName() + " is running.");
        Integer result = helloFuture.get();

        System.out.println(Thread.currentThread().getName() + " is done. result = " + result);
    }
}
