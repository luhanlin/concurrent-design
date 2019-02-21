package com.luhanlin.concurrentdesign.aa_thread_pool08;

/**
 * @description: 线程池拒绝策略
 * @author: Mr.Lu
 * @create: 2019-02-21 11:06
 **/
@FunctionalInterface
public interface ThreadDenyStrategy {

    void reject(Runnable runnable, ThreadPool threadPool);

    /**
     * 直接丢弃任务
     */
    class DiscardDenyPolicy implements ThreadDenyStrategy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            // do nothing
        }
    }

    /**
     * 向任务提交者抛出异常
     */
    class AbortDenyPolicy implements ThreadDenyStrategy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            throw new RunnableDenyException(" this runnable " + runnable + " will be abort. ");
        }
    }

    /**
     * 在当前线程执行任务
     */
    class RunnerDenyPolicy implements ThreadDenyStrategy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {

            if (!threadPool.isShutDown()){
                runnable.run();
            }
        }
    }

}
