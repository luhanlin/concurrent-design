package com.luhanlin.concurrentdesign.aa_thread08_pool;

/**
 * @description: 线程工厂,可以对线程进行个性化的定制
 * @author: Mr.Lu
 * @create: 2019-02-21 11:14
 **/
@FunctionalInterface
public interface ThreadFactory {

    Thread createThread(Runnable runnable);
}
