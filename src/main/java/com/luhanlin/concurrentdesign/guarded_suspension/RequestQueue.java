package com.luhanlin.concurrentdesign.guarded_suspension;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 类详细描述：存储 request 的类
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/23 5:57 PM
 */
public class RequestQueue {

    private final Queue<Request> queue = new LinkedList<>();

    public synchronized Request getRequest() throws InterruptedException {
        while (queue.peek() == null){
            wait();
        }
        return queue.remove();
    }

    public synchronized void putRequest(Request request){
        queue.offer(request);
        notifyAll();
    }


}
