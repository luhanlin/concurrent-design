package com.luhanlin.concurrentdesign.work_thread;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/28 5:16 PM
 */
public class WorkThread extends Thread{

    private final Channel channel;

    public WorkThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true){
            Request request = channel.getRequest();
            request.execute();
        }
    }
}
