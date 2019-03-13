package com.luhanlin.concurrentdesign.event_driven;

/**
 * event loop
 */
public interface DynamicRouter<E extends Message> {

    void registerChannel(Class<? extends E> classType, Channel<? extends E> channel);

    void dispatch(E message);
}
