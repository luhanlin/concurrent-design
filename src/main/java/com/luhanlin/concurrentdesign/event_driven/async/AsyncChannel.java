package com.luhanlin.concurrentdesign.event_driven.async;

import com.luhanlin.concurrentdesign.event_driven.Channel;
import com.luhanlin.concurrentdesign.event_driven.Event;
import lombok.AllArgsConstructor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: async event handler base
 * @author: Mr.Lu
 * @create: 2019-03-13 11:46
 **/
@AllArgsConstructor
public abstract class AsyncChannel<E extends Event> implements Channel<Event> {

    private final ExecutorService executorService;

    public AsyncChannel() {
        this(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
    }

    @Override
    public void dispatch(final Event message) {
        executorService.submit(() -> this.handle((E)message));
    }

    protected abstract void handle(E message);

    void stop(){
        if (executorService != null || !executorService.isShutdown()){
            executorService.shutdown();
        }
    }
}
