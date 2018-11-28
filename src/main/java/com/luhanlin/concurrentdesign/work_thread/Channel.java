package com.luhanlin.concurrentdesign.work_thread;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/28 5:15 PM
 */
public class Channel {

    private final WorkThread[] threadPool;
    private final Request[] requestQueue;
    private static final int MAX_REQUEST = 100;
    private int tail;
    private int head;
    private int count;

    public Channel(int pollSize) {
        threadPool = new WorkThread[pollSize];
        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i] = new WorkThread("Worker No." + i, this);
        }
        requestQueue = new Request[MAX_REQUEST];
        tail = 0;
        head = 0;
        count = 0;
    }

    // 获取request
    public synchronized Request getRequest(){
        while (count <= 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Request request = requestQueue[tail];
        tail = (tail + 1) % requestQueue.length;
        count--;
        this.notifyAll();
        return request;
    }

    public  synchronized void putRequest(Request request){
        while (count >= requestQueue.length){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        requestQueue[head] = request;
        head = (head + 1) % requestQueue.length;
        count++;
        notifyAll();
    }

    public void startWorkers(){
        for (WorkThread workThread: threadPool) {
            workThread.start();
        }
    }
}
