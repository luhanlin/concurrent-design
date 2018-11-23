package com.luhanlin.concurrentdesign.single_threaded_execution.practice;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/23 1:28 PM
 */
public class SecurityUserThread extends Thread {

    private SecurityGate gate;

    public SecurityUserThread(SecurityGate gate) {
        this.gate = gate;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            gate.enter();
            gate.exit();
        }
    }
}
