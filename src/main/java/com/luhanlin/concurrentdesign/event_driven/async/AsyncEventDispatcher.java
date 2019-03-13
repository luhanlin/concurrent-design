package com.luhanlin.concurrentdesign.event_driven.async;

import com.luhanlin.concurrentdesign.event_driven.Channel;
import com.luhanlin.concurrentdesign.event_driven.DispatchRuntimeException;
import com.luhanlin.concurrentdesign.event_driven.DynamicRouter;
import com.luhanlin.concurrentdesign.event_driven.Event;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: Async event loop base
 * @author: Mr.Lu
 * @create: 2019-03-13 11:56
 **/
public class AsyncEventDispatcher implements DynamicRouter<Event> {

    private final Map<Class<? extends Event>, AsyncChannel> table;

    public AsyncEventDispatcher() {
        this.table = new ConcurrentHashMap<>();
    }

    @Override
    public void registerChannel(Class<? extends Event> classType, Channel<? extends Event> channel) {
        if (!(channel instanceof AsyncChannel)) {
            throw new IllegalArgumentException("Channel must be the AsyncChannel type.");
        }

        this.table.put(classType, (AsyncChannel) channel);
    }

    @Override
    public void dispatch(Event message) {
        if (table.containsKey(message.getType())) {
            table.get(message.getType()).dispatch(message);
        } else {
            throw new DispatchRuntimeException("Can`t match the channel for [ "+ message.getType()+" ].");
        }
    }

    /**
     * 关闭所有的channel释放资源
     */
    public void shutdown(){
        table.values().forEach(AsyncChannel::stop);
    }
}
