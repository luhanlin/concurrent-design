package com.luhanlin.concurrentdesign.read_write_lock;

/**
 * 类详细描述：读取数据线程
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/28 10:04 AM
 */
public class ReadThread extends Thread {

    private final Data data;

    public ReadThread(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true){
                char[] read = data.read();
                System.out.println(Thread.currentThread().getName() + " reads: " + String.valueOf(read));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
