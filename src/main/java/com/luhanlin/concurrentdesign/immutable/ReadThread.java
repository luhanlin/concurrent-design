package com.luhanlin.concurrentdesign.immutable;

import java.util.List;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/23 4:45 PM
 */
public class ReadThread extends Thread {

    private final List<Integer> list;

    public ReadThread(List<Integer> list) {
        super("read-thread");
        this.list = list;
    }

    @Override
    public void run() {
        while (true){
//            synchronized (list){
                for (Integer i: list) {
                    System.out.println(i);
                }
//            }
        }
    }
}
