package com.luhanlin.concurrentdesign.work_thread;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/28 5:13 PM
 */
public class Main {

    public static void main(String[] args) {
        Channel channel = new Channel(8);

        channel.startWorkers();

        new ClientThread("Allen", channel).start();
        new ClientThread("Bob", channel).start();
        new ClientThread("Luc", channel).start();
        new ClientThread("Bella", channel).start();


    }
}
