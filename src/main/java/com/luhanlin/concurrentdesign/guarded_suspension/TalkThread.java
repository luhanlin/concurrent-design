package com.luhanlin.concurrentdesign.guarded_suspension;

/**
 * 类详细描述：对话线程
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/26 10:05 AM
 */
public class TalkThread extends Thread {

    private final RequestQueue input;
    private final RequestQueue output;

    public TalkThread(String name, RequestQueue input, RequestQueue output) {
        super(name);
        this.input = input;
        this.output = output;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Begin.");
        for (int i = 0; i < 20; i++) {
            Request request1 = null;
            try {
                request1 = input.getRequest();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " gets " + request1);

            Request request2 = new Request(request1.getName() + "!");

            output.putRequest(request2);
            System.out.println(Thread.currentThread().getName() + " puts " + request2);
        }

        System.out.println(Thread.currentThread().getName() + " end. ");

    }
}
