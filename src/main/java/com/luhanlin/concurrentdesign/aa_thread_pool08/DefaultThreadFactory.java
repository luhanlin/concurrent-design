package com.luhanlin.concurrentdesign.aa_thread_pool08;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: Mr.Lu
 * @create: 2019-02-21 13:37
 **/
public class DefaultThreadFactory implements ThreadFactory {

    private static final AtomicInteger GROUP_COUNT = new AtomicInteger(1);
    private static final ThreadGroup THREAD_GROUP = new ThreadGroup("MyThreadGroup"+ GROUP_COUNT.getAndDecrement());

    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    @Override
    public Thread createThread(Runnable runnable) {
        return new Thread(THREAD_GROUP, runnable, "My_thread_" + COUNTER.getAndIncrement());
    }
}
