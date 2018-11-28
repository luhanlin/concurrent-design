package com.luhanlin.concurrentdesign.thread_per_message;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/28 2:43 PM
 */
public class Host {

    private final Helper helper = new Helper();
    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    public void request(int count, char c){
        System.out.println("        request (" + count + " , " + c + ") Begin");

        new Thread(() -> {
            helper.handle(count, c);
        },"No." + atomicInteger.getAndIncrement()).start();

        System.out.println("        request (" + count + " , " + c + ") Begin");
    }

}
