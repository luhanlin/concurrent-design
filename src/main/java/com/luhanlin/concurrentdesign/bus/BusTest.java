package com.luhanlin.concurrentdesign.bus;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description:
 * @author: Mr.Lu
 * @create: 2019-03-12 09:37
 **/
public class BusTest {

    public static void main(String[] args) {
//        Bus syncBus = new SynEventBus("TestBus");
//
//        syncBus.register(new SimpleSubscriber());
//        syncBus.register(new SimpleSubscriber2());
//
//        syncBus.post("hello");
//        System.out.println("=======================");
//        syncBus.post("boy","test");
//
//        syncBus.post(100);
//        System.out.println("=======================");
//        syncBus.post(99,"test");

        Bus asyncBus = new AsynEventBus("TestBus", (ThreadPoolExecutor) Executors.newFixedThreadPool(5));

        asyncBus.register(new SimpleSubscriber());
        asyncBus.register(new SimpleSubscriber2());

        asyncBus.post("hello");
        System.out.println("=======================");
        asyncBus.post("boy","test");

        asyncBus.post(100);
        System.out.println("=======================");
        asyncBus.post(99,"test2");
    }
}
