package com.luhanlin.concurrentdesign.producter_consumer;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/27 12:14 PM
 */
public class Table {

    private String[] buffers;
    private int tail;       // 存放下一个值
    private int head;       // 获取下一个值
    private int count;      // buffer中存放的个数

    public Table(int count) {
        this.buffers = new String[count];
        this.tail = 0;
        this.head = 0;
        this.count = 0;
    }

    public synchronized String getCake() throws InterruptedException {
        while (count <= 0){
            System.out.println(Thread.currentThread().getName() + " have a rest by get cake");
            wait();
        }

        String cake = buffers[head];
        head = (head + 1) % buffers.length;
        count--;
        System.out.println(Thread.currentThread().getName() + " takes cake :" + cake);
        notifyAll();
        return cake;
    }

    public synchronized void putCake(String cake) throws InterruptedException {
        while (count >= buffers.length){
            System.out.println(Thread.currentThread().getName() + " have a rest by put cake");
            wait();
        }
        buffers[tail] = cake;
        tail = (tail + 1) % buffers.length;
        count++;
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " puts cake :" + cake);
    }


}
