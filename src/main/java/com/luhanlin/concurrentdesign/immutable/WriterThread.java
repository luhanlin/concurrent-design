package com.luhanlin.concurrentdesign.immutable;

import java.util.List;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/23 4:43 PM
 */
public class WriterThread extends Thread {

    private final List<Integer> list;

    public WriterThread(List<Integer> list) {
        super("writer-thread");
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; true; i++) {
            list.add(i);
            list.remove(0);
        }
    }
}
