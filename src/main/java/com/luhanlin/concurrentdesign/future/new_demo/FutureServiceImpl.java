package com.luhanlin.concurrentdesign.future.new_demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: Mr.Lu
 * @create: 2019-03-07 09:50
 **/
public class FutureServiceImpl<IN, OUT> implements FutureService<IN, OUT> {

    private static final String FUTURE_THREAD_PREFIX = "future-thread-";

    private final AtomicInteger next = new AtomicInteger(0);

    private String getNextName(){
        return FUTURE_THREAD_PREFIX + next.getAndIncrement();
    }

    @Override
    public Future<?> submit(Runnable runnable) {
        final FutureData<Void> futureData = new FutureData<>();
        new Thread(()->{
            runnable.run();
            futureData.finish(null);
        }, getNextName()).start();

        return futureData;
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input) {
        final FutureData<OUT> futureData = new FutureData<>();
        new Thread(()->{
            OUT result = task.get(input);
            futureData.finish(result);
        }, getNextName()).start();

        return futureData;
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input, Callable<IN> callable) {
        final FutureData<OUT> futureData = new FutureData<>();
        new Thread(()->{
            OUT result = task.get(input);
            futureData.finish(result);

            callable.call(input);
        }, getNextName()).start();

        return futureData;
    }

}
