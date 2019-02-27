package com.luhanlin.concurrentdesign.observer;

/**
 * @description: 任务生命周期定义
 * @author: Mr.Lu
 * @create: 2019-02-27 16:41
 **/
public interface TaskLifeCycle<T> {

    // 任务启动时进行触发
    void onStart(Thread thread);

    void onRunning(Thread thread);

    void onFinish(Thread thread, T result);

    void onError(Thread thread, Exception e);

    class EmptyLisfeCycle implements TaskLifeCycle{

        @Override
        public void onStart(Thread thread) {
            System.out.println(" start...");
        }

        @Override
        public void onRunning(Thread thread) {
            System.out.println(" running...");
        }

        @Override
        public void onFinish(Thread thread, Object result) {
            System.out.println(" done...");
        }

        @Override
        public void onError(Thread thread, Exception e) {
            System.out.println(" error...");
        }
    }

}
