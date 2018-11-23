package com.luhanlin.concurrentdesign.single_threaded_execution;

/**
 * 类详细描述：用户线程
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/22 5:28 PM
 */
public class UserThread implements Runnable {

    private final Gate gate;
    private final String name;
    private final String address;

    public UserThread(Gate gate, String name, String address) {
        this.gate = gate;
        this.name = name;
        this.address = address;
    }

    @Override
    public void run() {
        while (true){
            gate.pass(name, address);
        }
    }
}
