package com.luhanlin.concurrentdesign.aa_thread08_pool;

/**
 * @description:
 * @author: Mr.Lu
 * @create: 2019-02-21 13:28
 **/
public class ThreadTask {

    Thread thread;

    InternalThread internalThread;

    public ThreadTask(Thread thread, InternalThread internalThread) {
        this.thread = thread;
        this.internalThread = internalThread;
    }
}
