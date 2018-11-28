package com.luhanlin.concurrentdesign.read_write_lock;

import java.util.Random;

/**
 * 类详细描述：写入数据线程
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/28 10:08 AM
 */
public class WriteThread extends Thread {

    private final Data data;
    private final String str; // 需要写入的数据
    private final Random random;
    private int index;

    public WriteThread(Data data, String str) {
        this.data = data;
        this.str = str;
        random = new Random();
        index = 0;
    }

    @Override
    public void run() {
        try {
            while (true){
                char c = nextChar();
                data.write(c);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private char nextChar() {
        char c = str.charAt(index);
//        index++;
//        if (index >= str.length())
//            index = 0;
        index = (index + 1) % str.length();
        return c;
    }
}
