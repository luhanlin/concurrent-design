package com.luhanlin.concurrentdesign.aa_thread08_pool;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * @description: 线程池实现
 * @author: Mr.Lu
 * @create: 2019-02-21 13:20
 **/
public class BasicThreadPool implements ThreadPool {

    // 维护线程使用
    private final ThreadPoolRunnable internal_used_thread =  new ThreadPoolRunnable();

    // 线程池的初始线程数
    private final int initSize;

    // 线程池的最大线程数
    private final int maxSize;

    // 线程池的核心线程数
    private final int coreSize;

    // 线程池的活跃线程数
    private int activeCount;

    // 存放任务的任务队列
    private final RunnableQueue runnableQueue;

    // 工作线程的队列
    private final Queue<ThreadTask> threadTaskQueue = new ArrayDeque<>();

    // 创建线程的线程工厂
    private final static ThreadFactory THREAD_FACTORY = new DefaultThreadFactory();

    // 默认拒绝策略
    private final static ThreadDenyStrategy DEFAULT_DENY_STRATEGY = new ThreadDenyStrategy.DiscardDenyPolicy();

    // 线程池是否被关闭
    private volatile boolean isShutDown = false;

    // 线程活跃的时间
    private long keepAliveTime;

    // 线程活跃时间类型
    private final TimeUnit timeUnit;

    public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize) {
        this(initSize , maxSize, coreSize, queueSize, DEFAULT_DENY_STRATEGY, 10, TimeUnit.SECONDS);
    }

    public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize,
                           ThreadDenyStrategy denyStrategy, long keepAliveTime, TimeUnit timeUnit) {
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.runnableQueue = new BasicRunnableQueue(queueSize, denyStrategy, this);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        init();
    }

    private void init() {
        internal_used_thread.start();

        for (int i = 0; i < initSize; i++) {
            internal_used_thread.newThread();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (isShutDown){
            throw new IllegalThreadStateException("this thread pool is destroy .");
        }

        this.runnableQueue.offer(runnable);
    }

    @Override
    public void shutdown() {
        synchronized (internal_used_thread) {
            if (isShutDown) return;

            this.isShutDown = true;
            internal_used_thread.interrupt();
            // 停止线程池中所有线程
            threadTaskQueue.stream().forEach(threadTask -> {
                threadTask.internalThread.stop();
                threadTask.thread.interrupt();
            });

        }
    }

    @Override
    public int getInitSize() {
        if (isShutDown){
            throw new IllegalThreadStateException("this thread pool is destroy .");
        }

        return this.initSize;
    }

    @Override
    public int getMaxSize() {
        if (isShutDown){
            throw new IllegalThreadStateException("this thread pool is destroy .");
        }
        return this.maxSize;
    }

    @Override
    public int getCoreSize() {
        if (isShutDown){
            throw new IllegalThreadStateException("this thread pool is destroy .");
        }
        return this.coreSize;
    }

    @Override
    public int getQueueSize() {
        if (isShutDown){
            throw new IllegalThreadStateException("this thread pool is destroy .");
        }
        return this.runnableQueue.getSize();
    }

    @Override
    public int getActiveCount() {
        synchronized (internal_used_thread){
            return this.activeCount;
        }
    }

    @Override
    public boolean isShutDown() {
        return this.isShutDown;
    }

    /** 启动线程池单单线程使用类
     *      主要使用为做线程池状态维护,主要是扩充线程或者销毁
     */
    private class ThreadPoolRunnable extends Thread{

        @Override
        public void run() {
            while (!isShutDown && !isInterrupted()) {

                try {
                    timeUnit.sleep(keepAliveTime);
                } catch (InterruptedException e) {
                    isShutDown = true;
                    break;
                }

                synchronized (this){
                    // 当任务数大于活跃线程时，线程扩充至core size
                    if (runnableQueue.getSize() > 0 && activeCount < coreSize){
                        for (int i = initSize; i < coreSize; i++) {
                            newThread();
                        }
                        continue;
                    }

                    if (runnableQueue.getSize() > 0 && activeCount < maxSize){
                        for (int i = coreSize; i < maxSize; i++) {
                            newThread();
                        }
                    }

                    if (runnableQueue.getSize() == 0 && activeCount > coreSize){
                        for (int i = coreSize; i < activeCount; i++) {
                            removeThread();
                        }
                        continue;
                    }

                    if (runnableQueue.getSize() == 0 && activeCount > initSize){
                        for (int i = initSize; i < activeCount; i++) {
                            removeThread();
                        }
                    }
                }
            }
        }

        // 线程池中创建线程
        private void newThread() {
            InternalThread internalThread = new InternalThread(runnableQueue);
            Thread thread = THREAD_FACTORY.createThread(internalThread);
            ThreadTask threadTask = new ThreadTask(thread, internalThread);
            System.out.println(thread.getName() + " is created.");
            threadTaskQueue.offer(threadTask);
            // 因为线程的维护在单线程中执行，因此此处不用考虑多线程的问题
            activeCount ++;
            thread.start();
        }

        // 线程池销毁线程
        private void removeThread(){
            ThreadTask task = threadTaskQueue.remove();
            task.internalThread.stop();
            System.out.println(task.thread.getName() + " is destroyed.");
            activeCount--;
        }
    }
}
