package com.luhanlin.concurrentdesign.actor.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/12/4 10:41 AM
 */
public class AddStringClientThread extends Thread{

    private final ActiveObject activeObject;
    private String x = "1";
    private String y = "1";

    public AddStringClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                Future<String> result = activeObject.add(x, y);
                Thread.sleep(100);
                String resultValue = result.get();

                System.out.println(Thread.currentThread().getName() + ": "+ x+" +" + y +" = " + resultValue);

                x = y;
                y = resultValue;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
