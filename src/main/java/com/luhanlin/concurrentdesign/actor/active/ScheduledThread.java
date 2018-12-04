package com.luhanlin.concurrentdesign.actor.active;

/**
 * 类详细描述：执行请求队列中的线程
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/12/2 11:52 PM
 */
class ScheduledThread extends Thread{

    private final ActiveQueue queue;

    public ScheduledThread(ActiveQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){
            MethodRequest request = queue.takeRequest();
            request.execute();
        }
    }

    public void invoke(MethodRequest request) {
        queue.putRequest(request);
    }
}
