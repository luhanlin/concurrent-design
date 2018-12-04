package com.luhanlin.concurrentdesign.actor;

import com.luhanlin.concurrentdesign.actor.active.ActiveObject;
import com.luhanlin.concurrentdesign.actor.active.Result;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/12/4 9:43 AM
 */
public class AddClientThread extends Thread {

    private final ActiveObject activeObject;
    private String x = "1";
    private String y = "1";

    public AddClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                Result<String> result = activeObject.add(x, y);
                Thread.sleep(100);
                String resultValue = result.getResultValue();

                System.out.println(Thread.currentThread().getName() + ": "+ x+" +" + y +" = " + resultValue);

                x = y;
                y = resultValue;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
