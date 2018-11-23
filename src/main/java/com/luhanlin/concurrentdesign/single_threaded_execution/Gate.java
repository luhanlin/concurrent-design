package com.luhanlin.concurrentdesign.single_threaded_execution;

/**
 * 类详细描述：线程不安全的门类
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/22 5:28 PM
 */
public class Gate {

    private int count;
    private String name;
    private String adress;

    public synchronized void pass(String name, String adress){
        this.count++;
        this.name = name;
        this.adress = adress;
        check();
    }

    @Override
    public synchronized String toString() {
        return "No" + count + ": " + name + ", " + adress;
    }

    private void check() {
        if (this.name.charAt(0) != this.adress.charAt(0)){
            System.out.println("****** broken ******" + toString());
        }
    }


}
