package com.luhanlin.concurrentdesign.event_driven;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: event loop 实现
 * @author: Mr.Lu
 * @create: 2019-03-13 10:54
 **/
public class EventDispatcher implements DynamicRouter<Message> {

    private final Map<Class<? extends Message>, Channel> table;

    public EventDispatcher() {
        this.table = new ConcurrentHashMap<>();
    }

    @Override
    public void registerChannel(Class<? extends Message> classType, Channel<? extends Message> channel) {
        table.put(classType, channel);
    }

    @Override
    public void dispatch(Message message) {
        if (table.containsKey(message.getType())) {
            table.get(message.getType()).dispatch(message);
        } else {
            throw new DispatchRuntimeException("Can`t match the channel for [ "+ message.getType()+" ].");
        }
    }
}
