package com.luhanlin.concurrentdesign.bus;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @description: 注册类 不易暴露给外界
 * @author: Mr.Lu
 * @create: 2019-03-11 15:16
 **/
class Register {

    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<Subscriber>> containerSubscriber = new ConcurrentHashMap<>();

    public void bind(Object subscriber) {
        // 1. 获取 subscribe 标注的方法
        List<Method> methods = getSubscribeMethod(subscriber);

        methods.forEach(method -> tierSubscribe(subscriber, method));
    }

    public void unbind(Object subscriber) {
        containerSubscriber.forEach((topic, queue) -> {
            queue.forEach(s -> {
                if (s.getSubscriberObject() == subscriber) {
                    s.setDisable(true);
                }
            });
        });
    }

    public ConcurrentLinkedQueue<Subscriber> scanSubscriber(String topic){
        return containerSubscriber.get(topic);
    }

    /**
     * 获取 subscribe 标注的方法
     * @param subscriber
     * @return
     */
    private List<Method> getSubscribeMethod(Object subscriber) {
        final List<Method> methods = new ArrayList<>();

        Class<?> temp = subscriber.getClass();

        while(temp != null){
            Method[] declaredMethods = temp.getDeclaredMethods();

            // 只需要public 、只有一个参数 、被 {@link Subscribe } 修饰
            Arrays.asList(declaredMethods).stream()
                    .filter(method ->
                            method.getModifiers() == Modifier.PUBLIC &&
                                    method.getParameterCount() == 1 &&
                                    method.isAnnotationPresent(Subscribe.class)
                    )
                    .forEach(methods :: add);

            temp = temp.getSuperclass();
        }

        return methods;
    }

    /**
     * 将订阅信息存入容器中
     * @param subscriber
     * @param method
     */
    private void tierSubscribe(Object subscriber, Method method) {
        // 1. 获取方法上面的注解
        Subscribe subscribe = method.getDeclaredAnnotation(Subscribe.class);
        String topic = subscribe.topic();

        // 如果这个topic没有值则创建一个出来
        ConcurrentLinkedQueue<Subscriber> subscribers = containerSubscriber.computeIfAbsent(topic, k -> new ConcurrentLinkedQueue<>());

        subscribers.add(new Subscriber(subscriber, method));
    }
}
