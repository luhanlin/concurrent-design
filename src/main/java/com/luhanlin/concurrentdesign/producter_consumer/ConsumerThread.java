package com.luhanlin.concurrentdesign.producter_consumer;

import java.util.Random;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/27 12:31 PM
 */
public class ConsumerThread extends Thread {

    private final Table table;
    private final Random random;

    public ConsumerThread(String name, Table table, int seed) {
        super(name);
        this.table = table;
        this.random = new Random(seed);
    }

    @Override
    public void run() {
        try {
            while (true){
                table.getCake();
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
