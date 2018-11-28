package com.luhanlin.concurrentdesign.read_write_lock.reentrant;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/28 2:12 PM
 */
public class Main {

    public static void main(String[] args) {

        Database<String, String> database = new Database<>();

        new AssignThread(database,"Allen","Hello allen").start();
        new AssignThread(database,"Allen","Allen show time").start();
        new AssignThread(database,"Bella","Hello Bella").start();
        new AssignThread(database,"Bella","Bella show time").start();

        for (int i = 0; i < 100; i++) {
            new RetrieveThread(database,"Allen").start();
            new RetrieveThread(database,"Bella").start();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }
}
