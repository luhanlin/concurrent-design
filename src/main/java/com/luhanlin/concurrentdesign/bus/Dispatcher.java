package com.luhanlin.concurrentdesign.bus;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 * @description: event 分发
 * @author: Mr.Lu
 * @create: 2019-03-11 15:16
 **/
public class Dispatcher {

    private final Executor executorService;

    private final EventExceptionHandler handler;

    public static final Executor SEQ_EXECUTOR_SERVICE = SeqExecutorService.INSTANCE;

    public static final Executor PRE_THREAD_EXECUTOR_SERVICE = PreThreadExecutorService.INSTANCE;

    public Dispatcher(Executor executorService, EventExceptionHandler handler) {
        this.executorService = executorService;
        this.handler = handler;
    }

    /**
     * 工厂类
     * @param handler
     * @param executor
     * @return
     */
    public static Dispatcher newDispatcher(EventExceptionHandler handler, Executor executor) {
        return new Dispatcher(executor, handler);
    }

    public static Dispatcher seqDispatcher(EventExceptionHandler handler) {
        return new Dispatcher(SEQ_EXECUTOR_SERVICE, handler);
    }

    public static Dispatcher preThreadDispatcher(EventExceptionHandler handler) {
        return new Dispatcher(PRE_THREAD_EXECUTOR_SERVICE, handler);
    }

    public void dispatcher(Bus bus, Register register, Object event, String topic) {
        ConcurrentLinkedQueue<Subscriber> subscribers = register.scanSubscriber(topic);

        if (subscribers == null) {
            if (handler != null) {
                handler.handle(new IllegalArgumentException(" The topic " + topic + " not bind yet."),
                        new BaseEventContext(bus.getBusName(), null, event));
            }
            return;
        }

        subscribers.stream()
                .filter( subscriber -> !subscriber.isDisable())
                .filter( subscriber -> {
                    Method method = subscriber.getSubscriberMethod();

                    Class<?> aClass = method.getParameterTypes()[0];

                    return aClass.isAssignableFrom(event.getClass());
                })
                .forEach(subscriber -> realInvokeSubscriber(subscriber, bus, event));

    }

    private void realInvokeSubscriber(Subscriber subscriber, Bus bus, Object event) {
        Object obj = subscriber.getSubscriberObject();
        Method method = subscriber.getSubscriberMethod();

        try {
            method.invoke(obj, event);
        } catch (Exception e) {
            if (handler != null) {
                handler.handle(e, new BaseEventContext(bus.getBusName(), subscriber, event));
            }
        }

    }

    public void close() {
        if (executorService instanceof ExecutorService) {
            ((ExecutorService) executorService).shutdown();
        }

    }


    private static class SeqExecutorService implements Executor {

        private static final SeqExecutorService INSTANCE = new SeqExecutorService();

        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }

    private static class PreThreadExecutorService implements Executor {

        private static final PreThreadExecutorService INSTANCE = new PreThreadExecutorService();

        @Override
        public void execute(Runnable command) {
            new Thread(command).start();
        }
    }

    private class BaseEventContext implements EventContext {

        private final String busName;

        private final Subscriber subscriber;

        private final Object event;

        public BaseEventContext(String busName, Subscriber subscriber, Object event) {
            this.busName = busName;
            this.subscriber = subscriber;
            this.event = event;
        }

        @Override
        public String getResource() {
            return this.busName;
        }

        @Override
        public Object getSubscriber() {
            return subscriber == null ? subscriber.getSubscriberObject() : null;
        }

        @Override
        public Method getSubscribe() {
            return subscriber == null ? subscriber.getSubscriberMethod() : null;
        }

        @Override
        public Object getEvent() {
            return this.event;
        }
    }
}
