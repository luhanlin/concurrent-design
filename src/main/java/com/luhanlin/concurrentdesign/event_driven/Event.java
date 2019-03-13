package com.luhanlin.concurrentdesign.event_driven;

/**
 * @description: 事件基类
 * @author: Mr.Lu
 * @create: 2019-03-13 09:55
 **/
public class Event implements Message{

    @Override
    public Class<? extends Message> getType() {
        return getClass();
    }
}
