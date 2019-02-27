package com.luhanlin.concurrentdesign.observer;

/**
 * 类详细描述： 监控线程的生命周期
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/27 12:31 PM
 */
public interface Observable {

    enum ThreadCycle {
        STARTED, RUNNING, DONE, ERROR
    }

    ThreadCycle getCycle();

    void start();

    void interrupt();

}
