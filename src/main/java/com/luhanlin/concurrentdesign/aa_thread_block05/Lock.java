package com.luhanlin.concurrentdesign.aa_thread_block05;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * 锁 接口
 */
public interface Lock {

    void lock() throws InterruptedException;

    void lock(long mills) throws InterruptedException, TimeoutException;

    void unlock();

    List<Thread> getBlockedThreadList();

}
