package com.luhanlin.concurrentdesign.single_threaded_execution.practice;

import lombok.extern.log4j.Log4j2;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/23 11:03 AM
 */
@Log4j2
public class EaterThread implements Runnable {

    private String name;
    private Tool leftHand;
    private Tool rightHand;

    public EaterThread(String name, Tool leftHand, Tool rightHand) {
        this.name = name;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }

    @Override
    public void run() {
        while (true){
            eat();
        }
    }

    private void eat() {
        synchronized (leftHand){
           log.info("{} take up {}' leftHand(left).",name,leftHand);
           synchronized (rightHand){
               log.info("{} take up {}' rightHand(right).",name,rightHand);
               log.info("{} is eating.",name);
               log.info("{} puts down {}' rightHand(right).",name,rightHand);
           }
           log.info("{} puts down {}' leftHand(left).",name,leftHand);
        }
    }
}
