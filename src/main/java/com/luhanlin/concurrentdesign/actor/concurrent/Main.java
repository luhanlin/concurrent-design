package com.luhanlin.concurrentdesign.actor.concurrent;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/12/3 1:17 AM
 */
public class Main {

    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        try {

//            new MakingClientThread("Alice",activeObject).start();
//            new MakingClientThread("Bella",activeObject).start();
//            new DisplayClientThread("John",activeObject).start();
            new AddStringClientThread("Dash", activeObject).start();

            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " :" + e);
        } finally {
            activeObject.shutdown();

        }
    }
}
