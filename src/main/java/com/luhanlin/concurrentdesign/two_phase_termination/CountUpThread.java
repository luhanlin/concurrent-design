package com.luhanlin.concurrentdesign.two_phase_termination;

/**
 * 类详细描述：计数线程
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/29 3:42 PM
 */
public class CountUpThread extends Thread {

    private int counter;
    // 变为true 时线程终止
    private volatile boolean shutdownRequested = false;

    public void shutdownRequest(){
        shutdownRequested = true;

        // 打断正在等待的线程
        interrupt();
    }

    private boolean isShutdownRequested(){
        return shutdownRequested;
    }

    @Override
    public void run() {
        try {
            while (!isShutdownRequested()){
                doWork();
            }
        } catch (InterruptedException e) {

        } finally {
            doShutdown();
        }
    }

    private void doShutdown() {
        System.out.println("doShutdown doing counter = " + counter);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doWork() throws InterruptedException {
        counter++;
        System.out.println("doWork counter = " + counter);
        Thread.sleep(1000);
    }
}
