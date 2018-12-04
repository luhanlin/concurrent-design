package com.luhanlin.concurrentdesign.actor.active;

/**
 * 类详细描述：存放 MethodRequest 的类
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/12/2 11:56 PM
 */
class ActiveQueue {

    private final int MAX_QUEUE_SIZE = 100;
    private final MethodRequest[] requests;

    private int tail;       // 存放的下一个位置
    private int head;       // 取值的下一个位置
    private int count;

    public ActiveQueue() {
        this.requests = new MethodRequest[MAX_QUEUE_SIZE];
        this.tail = 0;
        this.head = 0;
        this.count = 0;
    }

    public synchronized MethodRequest takeRequest(){
        while (count <= 0){
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        MethodRequest request = requests[head];
        head = (head + 1) % requests.length;

        count--;
        notifyAll();
        return request;
    }

    public synchronized void putRequest(MethodRequest methodRequest){
        while (count >= requests.length){
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        requests[tail] = methodRequest;
        tail = (tail + 1) % requests.length;
        count++;
        notifyAll();
    }
}
