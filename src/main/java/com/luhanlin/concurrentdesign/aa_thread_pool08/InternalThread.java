package com.luhanlin.concurrentdesign.aa_thread_pool08;

/**
 * @description: 线程专用类，用于循环调取RunnableQueue中的任务进行执行
 * @author: Mr.Lu
 * @create: 2019-02-21 10:37
 **/
public class InternalThread implements Runnable{

    private final RunnableQueue runnableQueue;

    private volatile boolean isRunning = true;

    public InternalThread(RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
        while (isRunning){
            try {
                Runnable task = runnableQueue.take();
                task.run();
            } catch (InterruptedException e) {
                stop();
            }
        }
    }

    /**
     * 停止当前线程
     */
    public void stop(){
        this.isRunning = false;
    }
}
