package com.luhanlin.concurrentdesign.actor.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 类详细描述：制造string的线程
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/12/2 11:23 PM
 */
public class MakingClientThread extends Thread{

    private final ActiveObject activeObject;
    private final char fillchar;

    public MakingClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
        this.fillchar = name.charAt(0);
    }

    @Override
    public void run() {
        try {
            for (int i = 1; true; i++) {
                Future<String> result = activeObject.makingString(i, fillchar);
                Thread.sleep(10);

                String value = result.get();
                System.out.println(Thread.currentThread().getName() + " : value = " + value);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
