package com.luhanlin.concurrentdesign.event_driven;

/**
 * event handler
 * @param <E>
 */
public interface Channel<E extends Message> {

    void dispatch(E message);
}
