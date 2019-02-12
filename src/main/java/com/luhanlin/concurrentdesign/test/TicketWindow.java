package com.luhanlin.concurrentdesign.test;

import java.util.stream.IntStream;

public class TicketWindow extends Thread {

    private final String name;

    // 号码变大会出现线程安全问题
    private static int index = 1;

    // 此种方式不保证原子性
//    private volatile int index = 1;

    private static final int MAX = 50;

    public static void main(String[] args) {
//        new TicketWindow("No.01").start();
//        new TicketWindow("No.02").start();
//        new TicketWindow("No.03").start();
//        new TicketWindow("No.04").start();


        /** 此种方式也会出现线程安全的问题 */
//        TicketTwo ticketTwo = new TicketWindow.TicketTwo();
//
//        new Thread(ticketTwo, "No.01").start();
//        new Thread(ticketTwo, "No.02").start();
//        new Thread(ticketTwo, "No.03").start();
//        new Thread(ticketTwo, "No.04").start();

    }

    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (index <= MAX){
            System.out.println("it is the " + (index++) + " ticket");
        }
    }

    // 实现runnable 接口
    static class TicketTwo implements Runnable{

        private int index2 = 1;

        private static final int MAX2 = 50;

        @Override
        public void run() {
            while (index2 <= MAX2){
                System.out.println("it is the " + (index2++) + " ticket");
            }
        }
    }

}
