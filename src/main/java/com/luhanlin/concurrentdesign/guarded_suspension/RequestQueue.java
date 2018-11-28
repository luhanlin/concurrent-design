package com.luhanlin.concurrentdesign.guarded_suspension;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 类详细描述：存储 request 的类 测试使用两种超时方法
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/23 5:57 PM
 */
public class RequestQueue {

    // 设定超时时间变量
    private static final long TIMEOUT = 5000L;

//    private final Queue<Request> queue = new LinkedList<>();

    private final BlockingQueue<Request> queue = new LinkedBlockingDeque<>();

//    public synchronized Request getRequest() {
//        long start = System.currentTimeMillis();
//
//        while (queue.peek() == null){
//            long now = System.currentTimeMillis();
//            long rest = TIMEOUT - (now - start);
//            System.out.println("rest = " + rest);
//            if (rest <= 0){
//                throw new LivenessException(" thrown by " + Thread.currentThread().getName());
//            }
//            try {
//                wait(rest);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        return queue.remove();
//    }

//    public synchronized void putRequest(Request request){
//        queue.offer(request);
//        notifyAll();
//    }

    public Request getRequest(){
        Request req = null;
        try {
            req = queue.poll(TIMEOUT, TimeUnit.MILLISECONDS);
            if (req == null){
                throw new LivenessException("thrown by " + Thread.currentThread().getName());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return req;
    }

    public void putRequest(Request request){
        try {
            queue.offer(request, TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
