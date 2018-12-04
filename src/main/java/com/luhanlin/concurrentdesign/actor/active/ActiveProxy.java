package com.luhanlin.concurrentdesign.actor.active;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/12/2 11:44 PM
 */
class ActiveProxy implements ActiveObject{

    private final ScheduledThread scheduledThread;
    private final Servant servant;

    public ActiveProxy(ScheduledThread scheduledThread, Servant servant) {
        this.scheduledThread = scheduledThread;
        this.servant = servant;
    }

    @Override
    public Result<String> makingString(int count, char c) {
        FutureResult<String> future = new FutureResult<>();
        // 讲请求封装成对象，并将对象添加到请求队列中
        scheduledThread.invoke(new MakingStringRequest(servant, future, count, c));
        return future;
    }

    @Override
    public void displayString(String str) {
        scheduledThread.invoke(new DisplayStringRequest(servant, str));
    }

    @Override
    public Result<String> add(String x, String y) {
        FutureResult<String> future = new FutureResult<>();
        // 讲请求封装成对象，并将对象添加到请求队列中
        scheduledThread.invoke(new AddStringRequest(servant, future, x, y));
        return future;
    }
}
