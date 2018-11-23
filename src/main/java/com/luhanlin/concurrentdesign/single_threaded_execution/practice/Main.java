package com.luhanlin.concurrentdesign.single_threaded_execution.practice;

/**
 * 类详细描述：client
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/23 11:09 AM
 */
public class Main {

    public static void main(String[] args) {
        // 测试死锁
//        Tool fork = new Tool("fork");
//        Tool spoon = new Tool("spoon");
//
//        new Thread(new EaterThread("Alice", spoon, fork)).start();
//        new Thread(new EaterThread("Bob", fork, spoon)).start();

        System.out.println(" Testing SecurityGate starting ...");
        SecurityGate securityGate = new SecurityGate();
        SecurityUserThread[] securityUserThreads = new SecurityUserThread[5];

        for (int trail = 0; true ; trail++) {

            for (int i = 0; i < securityUserThreads.length; i++) {
                securityUserThreads[i] = new SecurityUserThread(securityGate);
                securityUserThreads[i].start();
            }
            for (int i = 0; i < securityUserThreads.length; i++) {
                try {
                    securityUserThreads[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (securityGate.getCount() == 0){
//                System.out.println(".");
            } else {
                System.out.println(" SecurityGate is not safe .");
                System.out.println(" count = " + securityGate.getCount());
                System.out.println(" trail = " + trail);
                break;
            }
        }

    }
}
