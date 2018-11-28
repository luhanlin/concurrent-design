package com.luhanlin.concurrentdesign.read_write_lock.reentrant;

import java.util.Random;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/28 2:07 PM
 */
public class AssignThread extends Thread {

    private final Random random = new Random();
    private final Database<String, String> database;
    private final String key;
    private final String value;

    public AssignThread(Database<String, String> database, String key, String value) {
        this.database = database;
        this.key = key;
        this.value = value;
    }

    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName() + " : assign( " + key + "," + value + ")");
            database.assign(key, value);

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
