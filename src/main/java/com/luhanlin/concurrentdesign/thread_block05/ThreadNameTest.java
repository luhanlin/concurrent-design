package com.luhanlin.concurrentdesign.thread_block05;

import java.util.stream.IntStream;

public class ThreadNameTest {

    private static final String PREFIX = "ALTER_";

    public static void main(String[] args) {
        // 创建5个线程，输出线程名字
//        IntStream.range(0,5).boxed()
//                .map(integer -> new Thread(() -> System.out.println(Thread.currentThread().getName())))
//                .forEach(thread -> thread.start());

        // 创建 5 个自定义命名的线程
        IntStream.range(0, 5).boxed().map(ThreadNameTest::createThread).forEach(Thread::start);

    }

    // 创建线程
    private static Thread createThread(final int initNum){
        return new Thread(() -> System.out.println(Thread.currentThread().getName()), PREFIX + initNum);
    }

}
