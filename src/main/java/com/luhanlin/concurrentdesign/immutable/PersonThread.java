package com.luhanlin.concurrentdesign.immutable;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/23 4:38 PM
 */
public class PersonThread extends Thread {

    private Person person;

    public PersonThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":" +person);
    }
}
