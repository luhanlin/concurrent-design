package com.luhanlin.concurrentdesign.read_write_lock;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/28 10:14 AM
 */
public class Main {

    public static void main(String[] args) {
        Data data = new Data(8);

        new ReadThread(data).start();
        new ReadThread(data).start();
        new ReadThread(data).start();
        new ReadThread(data).start();

        new WriteThread(data, "ABCDEFGHIJKLMNOPQRSTUVWSYZ").start();
        new WriteThread(data, "abcdefghijklmnopqrstuvwsyz").start();

    }
}
