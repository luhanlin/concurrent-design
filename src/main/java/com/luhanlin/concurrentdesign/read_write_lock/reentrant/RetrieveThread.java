package com.luhanlin.concurrentdesign.read_write_lock.reentrant;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类详细描述：检索线程
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/28 12:02 PM
 */
public class RetrieveThread extends Thread{

    private final Database<String,String> database;
    private final String key;
    private static final AtomicInteger atomicInteger = new AtomicInteger(0);

    public RetrieveThread(Database<String, String> database, String key) {
        this.database = database;
        this.key = key;
    }

    @Override
    public void run() {
        while (true){
            int count = atomicInteger.getAndIncrement();
            String value = database.retrive(key);
            System.out.println(count + " : " + key + " => " + value);
        }
    }
}
