package com.luhanlin.concurrentdesign.balking;

import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

/**
 * 类详细描述：测试超时实用类
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/26 2:43 PM
 */
public class Hosts {

    private final long timeout;
    private boolean ready;

    public Hosts(long timeout) {
        this.timeout = timeout;
        this.ready = false;
    }

    public synchronized void chanded(boolean on){
        this.ready = on;
        notifyAll();
    }

    public synchronized void execute() throws TimeoutException, InterruptedException {
        long start = System.currentTimeMillis();
        System.out.println(start);

        while (!ready){
            long now = System.currentTimeMillis();
            System.out.println(now);
            // 剩余的等待时间
            long rest = timeout - (now - start);
            System.out.println(" wait time = " + rest);
            if (rest <= 0){
                throw new TimeoutException("now -start = " + (now - start) + ", timeout = " + timeout);
            }
            wait(rest);
        }

        doExecute();
    }

    private void doExecute() {
        System.out.println(Thread.currentThread().getName() + " calls doExecute");
    }
}
