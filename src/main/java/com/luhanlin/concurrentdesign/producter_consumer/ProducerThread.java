package com.luhanlin.concurrentdesign.producter_consumer;

import java.util.Random;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/27 12:25 PM
 */
public class ProducerThread extends Thread {

    private final Table table;
    private final Random random;
    private static int id = 0;

    public ProducerThread(String name, Table table, int seed) {
        super(name);
        this.table = table;
        this.random = new Random(seed);
    }

    @Override
    public void run() {
        try {
            while (true){
                String cake = " Cake No." + nextId() + " by " + getName();
                table.putCake(cake);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static synchronized int nextId() {
        return id++;
    }
}
