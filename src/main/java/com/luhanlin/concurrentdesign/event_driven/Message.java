package com.luhanlin.concurrentdesign.event_driven;

/**
 * @description: 事件消息体
 * @author: Mr.Lu
 * @create: 2019-03-13 09:51
 **/
public interface Message {

    Class<? extends Message> getType();
}
