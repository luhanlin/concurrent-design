package com.luhanlin.concurrentdesign.aa_thread08_pool;

import java.util.LinkedList;

/**
 * @description: 任务队列的实现类
 * @author: Mr.Lu
 * @create: 2019-02-21 10:45
 **/
public class BasicRunnableQueue implements RunnableQueue {

    // 队列最大的存放数
    private final int limit;

    // 存放任务的集合
    private final LinkedList<Runnable> runnableList = new LinkedList<>();

    // 拒绝策略
    private final ThreadDenyStrategy denyStrategy;

    private final ThreadPool threadPool;

    public BasicRunnableQueue(int limit, ThreadDenyStrategy denyStrategy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyStrategy = denyStrategy;
        this.threadPool = threadPool;
    }

    @Override
    public void offer(Runnable runnable) {
        synchronized (runnableList){
            System.out.println(" add runnable " + runnable);
            // 队列中任务数达到范围执行拒绝策略
            if (runnableList.size() >= limit) {
                denyStrategy.reject(runnable, threadPool);
            }

            // 添加任务，唤醒执行线程
            runnableList.addLast(runnable);
            runnableList.notifyAll();
        }

    }

    @Override
    public Runnable take() throws InterruptedException {
        synchronized (runnableList) {
            // 队列中没有任务则进入等待状态
            if (runnableList.isEmpty()) {
                runnableList.wait();
            }

            return runnableList.removeFirst();
        }

    }

    @Override
    public int getSize() {
        synchronized (runnableList){
            return this.runnableList.size();
        }
    }
}
