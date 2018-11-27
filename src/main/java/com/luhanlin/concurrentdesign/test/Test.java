package com.luhanlin.concurrentdesign.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/22 3:11 PM
 */
public class Test {

    public static void main(String[] args) {
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        threadFactory.newThread(new TestRunnable("hello world")).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("12123231");
        }
    }
}
