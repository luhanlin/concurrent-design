package com.luhanlin.concurrentdesign.thread_api;

public class ThreadPriority {

    public static void main(String[] args) {

        // 测试线程优先级
//        Thread t1 = new Thread(() -> {
//            while (true){
//                System.out.println("t1");
//            }
//        });
//
//        Thread t2 = new Thread(() -> {
//            while (true){
//                System.out.println("t2");
//            }
//        });
//
//        t1.setPriority(3);
//        t2.setPriority(10);
//
//        t1.start();
//        t2.start();

        // 查看线程的优先级
        Thread t1 = new Thread();
        System.out.println(" t1 priority is " + t1.getPriority()); // 5

        Thread t2 = new Thread(() -> {
            Thread t3 = new Thread();
            System.out.println(" t3 priority is " + t3.getPriority()); // 6
        });

        t2.setPriority(6);

        t2.start();

        System.out.println(" t3 priority is " + t2.getPriority()); // 6
    }

}
