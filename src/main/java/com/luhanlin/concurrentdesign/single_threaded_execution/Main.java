package com.luhanlin.concurrentdesign.single_threaded_execution;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/22 4:47 PM
 */
public class Main {

    public static void main(String[] args) {
        Gate gate = new Gate();

        new Thread(new UserThread(gate,"Allen", "Ale")).start();
        new Thread(new UserThread(gate,"Bob", "Bad")).start();
    }
}
