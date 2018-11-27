package com.luhanlin.concurrentdesign.test;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/22 3:14 PM
 */
public class TestRunnable implements Runnable{

    private String name;

    public TestRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(name);
        }
    }
}
