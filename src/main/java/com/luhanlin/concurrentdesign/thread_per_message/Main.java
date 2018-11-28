package com.luhanlin.concurrentdesign.thread_per_message;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/28 2:56 PM
 */
public class Main {

    public static void main(String[] args) {

//        Host host = new Host();
//
//        host.request(10, 'A');
//        host.request(20, 'B');
//        host.request(30, 'C');

        BlackHole blackHole = new BlackHole();
        blackHole.enter(new Object());

    }
}
