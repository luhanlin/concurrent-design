package com.luhanlin.concurrentdesign.single_threaded_execution.practice;

import lombok.Getter;

/**
 * 类详细描述：线程不安全的门类
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/22 5:28 PM
 */
public class SecurityGate {

    @Getter
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

    public void exit(){
        int counter = count;
        Thread.yield();
        count = counter - 1;
    }

    public void enter(){
        int counter = count;
        Thread.yield();
        count = counter + 1;
    }

}
