package com.luhanlin.concurrentdesign.bus;

import java.util.concurrent.Executor;

/**
 * @description: 同步 EventBus
 * @author: Mr.Lu
 * @create: 2019-03-11 15:10
 **/
public class SynEventBus implements Bus{

    private final Register register;

    private String busName;

    private static final String DEFAULT_TOPIC  = "default_topic";

    private static final String DEFAULT_BUS_NAME  = "default_bus_name";

    private final Dispatcher dispatcher;

    public SynEventBus(String busName, EventExceptionHandler handler, Executor executor) {
        this.register = new Register();
        this.busName = busName;
        this.dispatcher = Dispatcher.newDispatcher(handler, executor);
    }

    public SynEventBus() {
        this(DEFAULT_BUS_NAME, null, Dispatcher.SEQ_EXECUTOR_SERVICE);
    }

    public SynEventBus(String busName) {
        this(busName, null, Dispatcher.SEQ_EXECUTOR_SERVICE);
    }

    public SynEventBus(EventExceptionHandler handler) {
        this(DEFAULT_BUS_NAME, handler, Dispatcher.SEQ_EXECUTOR_SERVICE);
    }

    public Register getRegister(){
        return register;
    }

    @Override
    public void register(Object subscriber) {
        this.register.bind(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        this.register.unbind(subscriber);
    }

    @Override
    public void post(Object event) {
        this.post(event, DEFAULT_TOPIC);
    }

    @Override
    public void post(Object event, String topic) {
        this.dispatcher.dispatcher(this, register, event, topic);
    }

    @Override
    public void close() {
        this.dispatcher.close();
    }

    @Override
    public String getBusName() {
        return this.busName;
    }
}
