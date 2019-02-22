package com.luhanlin.concurrentdesign.aa_thread08_pool;

/**
 * @description: 定义线程池的接口规范
 * @author: Mr.Lu
 * @create: 2019-02-21 10:48
 **/
public interface ThreadPool {

    /**
     * 提交任务到线程池
     */
    void execute(Runnable runnable);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 获取线程池初始化大小
     * @return
     */
    int getInitSize();

    /**
     * 获取线程池线程最大值
     * @return
     */
    int getMaxSize();

    /**
     * 获取线程池核心线程数
     * @return
     */
    int getCoreSize();

    /**
     * 获取任务队列大小
     * @return
     */
    int getQueueSize();

    /**
     * 获取活跃线程数
     * @return
     */
    int getActiveCount();

    /**
     * 线程池是否被关闭
     * @return
     */
    boolean isShutDown();

}
