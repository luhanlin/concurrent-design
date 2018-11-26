package com.luhanlin.concurrentdesign.guarded_suspension;

import java.util.Random;

/**
 * 类详细描述：发送请求的线程
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/23 6:01 PM
 */
public class ClientThread extends Thread {

    private final Random random;
    private final RequestQueue requestQueue;

    public ClientThread(RequestQueue requestQueue, String name, int seed) {
        super(name);
        this.random = new Random(seed);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10000; i++) {
                Request request = new Request("No." + i);
                System.out.println(Thread.currentThread().getName() + " requests :" + request);

                requestQueue.putRequest(request);

                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
