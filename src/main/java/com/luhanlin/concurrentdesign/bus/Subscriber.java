package com.luhanlin.concurrentdesign.bus;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: Mr.Lu
 * @create: 2019-03-11 16:19
 **/
public class Subscriber {

    private final Object subscriber;

    private final Method method;

    private boolean disable;

    public Subscriber(Object subscriber, Method method) {
        this.subscriber = subscriber;
        this.method = method;
    }

    public Object getSubscriberObject() {

        return this.subscriber;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public Method getSubscriberMethod() {
        return this.method;
    }

    public boolean isDisable() {
        return this.disable;
    }
}
