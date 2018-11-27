package com.luhanlin.concurrentdesign.balking;

import java.util.Random;

/**
 * 类详细描述：修改内容并进行手动保存
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/26 1:40 PM
 */
public class ChangerThread extends Thread {

    private final Data data;
    private final Random random;

    public ChangerThread(String name, Data data) {
        super(name);
        this.data = data;
        this.random = new Random();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 30; i++) {
                data.change("No." + i);
                Thread.sleep(random.nextInt(1000));
                data.save();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
