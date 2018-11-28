package com.luhanlin.concurrentdesign.producter_consumer;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/27 12:14 PM
 */
public class Main {

    public static void main(String[] args) {
        Table table = new Table(3);

        new ProducerThread("Producer_thread_01", table, 31415).start();
        new ProducerThread("Producer_thread_02", table, 34532).start();
        new ProducerThread("Producer_thread_03", table, 98574).start();
        new ConsumerThread("Consumer_thread_01", table, 65445).start();
        new ConsumerThread("Consumer_thread_02", table, 45778).start();
        new ConsumerThread("Consumer_thread_03", table, 98765).start();

    }
}
