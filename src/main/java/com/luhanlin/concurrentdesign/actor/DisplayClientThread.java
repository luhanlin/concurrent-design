package com.luhanlin.concurrentdesign.actor;

import com.luhanlin.concurrentdesign.actor.active.ActiveObject;

/**
 * 类详细描述：显示字符串的线程
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/12/2 11:28 PM
 */
public class DisplayClientThread extends Thread{

    private final ActiveObject activeObject;

    public DisplayClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                activeObject.displayString(Thread.currentThread().getName() + i);
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
