package com.luhanlin.concurrentdesign.observer;

import java.util.concurrent.TimeUnit;

/**
 * @description: 线程运行监控测试
 * @author: Mr.Lu
 * @create: 2019-02-27 17:49
 **/
public class ObservableTest {

    public static void main(String[] args) {
        Observable observableThread = new ObservableThread<>(() -> {
            TimeUnit.SECONDS.sleep(10);

            System.out.println("finish done.");
            return "hello";
        });
        observableThread.start();

//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//
//        }
//        observableThread.interrupt();
    }
}
