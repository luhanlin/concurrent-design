package com.luhanlin.concurrentdesign.thread_per_message;

/**
 * 类详细描述：具体处理工作的类
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/28 2:43 PM
 */
public class Helper {

    public void handle(int count, char c){
        System.out.println(Thread.currentThread().getName() + "        handle (" + count + " , " + c + ") Begin");
        for (int i = 0; i < count; i++) {
            slowly();
            System.out.print(c);
        }
        System.out.println();
        System.out.println(Thread.currentThread().getName() + "        handle (" + count + " , " + c + ") End");
    }

    private void slowly() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
