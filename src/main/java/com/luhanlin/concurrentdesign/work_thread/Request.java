package com.luhanlin.concurrentdesign.work_thread;

import java.util.Random;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/28 5:18 PM
 */
public class Request {

    private final Random random = new Random();

    private String name;
    private int num;

    public Request(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public void execute(){
        System.out.println(Thread.currentThread().getName() + " executes " + this);
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return " Request from " + name + " No." + num;
    }
}
