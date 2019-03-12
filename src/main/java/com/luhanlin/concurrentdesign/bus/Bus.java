package com.luhanlin.concurrentdesign.bus;

public interface Bus {

    /**
     * 注册
     * @param subscriber
     */
    void register(Object subscriber);

    /**
     * 取消注册，取消注册后不再接受任何来自bus 的信息
     * @param subscriber
     */
    void unregister(Object subscriber);

    /**
     * 提交 event 到默认的topic
     * @param event
     */
    void post(Object event);

    /**
     * 提交 event 到指定的topic
     * @param event
     * @param topic
     */
    void post(Object event, String topic);

    /**
     * 关闭bus
     */
    void close();

    /**
     * 获取bus名称
     * @return
     */
    String getBusName();
}
