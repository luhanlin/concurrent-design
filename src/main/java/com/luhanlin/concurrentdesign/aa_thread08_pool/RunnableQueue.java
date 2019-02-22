package com.luhanlin.concurrentdesign.aa_thread08_pool;

/**
 * @description: 存放需要执行的runnable
 * @author: Mr.Lu
 * @create: 2019-02-21 10:41
 **/
public interface RunnableQueue {

    /**
     * 提交任务到队列
     * @param runnable
     */
    void offer(Runnable runnable);

    /**
     * 从队列获取任务
     * @return
     */
    Runnable take() throws InterruptedException;

    /**
     * 当前队列的大小
     * @return
     */
    int getSize();

}
