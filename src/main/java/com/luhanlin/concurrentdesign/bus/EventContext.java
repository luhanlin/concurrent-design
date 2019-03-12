package com.luhanlin.concurrentdesign.bus;

import java.lang.reflect.Method;

/**
 * @description: 事件上下文，主要提供给{@link EventExceptionHandler#handle(Throwable, EventContext)} }使用
 * @author: Mr.Lu
 * @create: 2019-03-11 15:23
 **/
public interface EventContext {

    /**
     * 获取Event 来源
     * @return
     */
    String getResource();

    /**
     * 获取订阅号
     * @return
     */
    Object getSubscriber();

    /**
     * 获取订阅号进行使用的方法
     * @return
     */
    Method getSubscribe();

    /**
     * 获取事件体
     * @return
     */
    Object getEvent();
}
