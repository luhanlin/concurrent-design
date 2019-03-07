package com.luhanlin.concurrentdesign.future.new_demo;

/**
 * @description: 进行线程的提交
 * @author: Mr.Lu
 * @create: 2019-03-07 09:35
 **/
public interface FutureService<IN, OUT> {

    /**
     * 没有返回值的提交
     * @param runnable
     * @return
     */
    Future<?> submit(Runnable runnable);

    /**
     * 自定义任务的提交
     * @param task
     * @param input
     * @return
     */
    Future<OUT> submit(Task<IN, OUT> task, IN input);

    /**
     * 自定义任务并且有回调函数的提交
     * @param task
     * @param input
     * @param callable
     * @return
     */
    Future<OUT> submit(Task<IN, OUT> task, IN input, Callable<IN> callable);

    static <IN, OUT> FutureService<IN, OUT> getFutureService(){
        return new FutureServiceImpl();
    }
}
