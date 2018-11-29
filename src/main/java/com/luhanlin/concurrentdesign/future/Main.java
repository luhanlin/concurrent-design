package com.luhanlin.concurrentdesign.future;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/29 9:57 AM
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(" Main begin ");
        Host host = new Host();

        Data a = host.request(10, 'A');
        Data b = host.request(20, 'B');
        Data c = host.request(-1, 'C');

        // 处理其他事物
        try {
            System.out.println(" do other things begin");
            Thread.sleep(2000);
            System.out.println(" do other things end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("data01 = " + a.getContent());
        System.out.println("data02 = " + b.getContent());
        System.out.println("data03 = " + c.getContent());

        System.out.println(" Main end ");
    }
}
