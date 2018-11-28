package com.luhanlin.concurrentdesign.work_thread;

import java.util.Random;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/28 6:36 PM
 */
public class ClientThread extends Thread {

    private final Channel channel;
    private final Random random;

    public ClientThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
        this.random = new Random();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                Request request = new Request(getName(), i);
                channel.putRequest(request);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
