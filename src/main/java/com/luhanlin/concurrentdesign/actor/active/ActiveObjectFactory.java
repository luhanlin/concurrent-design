package com.luhanlin.concurrentdesign.actor.active;

/**
 * 类详细描述：ActiveObject 工厂类
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/12/2 11:21 PM
 */
public class ActiveObjectFactory {

    // 表示实际执行处理的类
//    private final Servant servant;
    // 按照顺序保存需要执行的 MethodRequest 的类
//    private final ActiveQueue queue;
    // 执行queue中保存的request的线程
//    private final ScheduledThread scheduledThread;
    // 讲方法调用转换成为MethodRequest 的类，实现 Command 模式
//    private final ActiveProxy proxy;

    public static ActiveObject createActiveObject() {
        Servant servant = new Servant();
        ActiveQueue queue = new ActiveQueue();
        ScheduledThread scheduledThread = new ScheduledThread(queue);
        ActiveProxy proxy = new ActiveProxy(scheduledThread, servant);
        scheduledThread.start();
        return proxy;
    }
}
