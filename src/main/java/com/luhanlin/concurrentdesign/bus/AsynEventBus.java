package com.luhanlin.concurrentdesign.bus;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description: 异步 EventBus
 * @author: Mr.Lu
 * @create: 2019-03-11 15:49
 **/
public class AsynEventBus extends SynEventBus {

    public AsynEventBus(String busName, EventExceptionHandler handler, ThreadPoolExecutor executor) {
        super(busName, handler, executor);
    }

    public AsynEventBus(ThreadPoolExecutor executor) {
        this("default_async", null, executor);
    }

    public AsynEventBus(String busName, ThreadPoolExecutor executor) {
        this(busName, null, executor);
    }

    public AsynEventBus(EventExceptionHandler handler, ThreadPoolExecutor executor) {
        this("default_async", handler, executor);
    }

}
