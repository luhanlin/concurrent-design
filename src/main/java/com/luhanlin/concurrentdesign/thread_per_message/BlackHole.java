package com.luhanlin.concurrentdesign.thread_per_message;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/28 4:05 PM
 */
public class BlackHole {

    public void enter(Object obj){
        System.out.println(" step 1");

        magic(obj);
        System.out.println(" step 2");

        synchronized (obj){
            System.out.println(" step 3 (never reached here)");
        }
    }

    private void magic(final Object obj) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                synchronized (obj) {
                    System.out.println(" enter magic thread ");
                    synchronized (this){
                        System.out.println(" enter magic this ");
                        this.notifyAll();
                    }
                    try {
                        this.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        synchronized (thread) {
            System.out.println(" enter here by sy");
            thread.start();

            try {
                thread.wait();
                System.out.println(" enter here by wait");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
