package com.luhanlin.concurrentdesign.aa_thread09_classloader;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @description: 多线程安全测试
 * @author: Mr.Lu
 * @create: 2019-02-22 14:04
 **/
public class ClassInit {

    static {
        try {
            System.out.println(" The class init static code block will be invoke.");
            TimeUnit.SECONDS.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        IntStream.range(0,10).forEach(i -> {
//            new Thread(ClassInit::new);
//        });

        for (int i = 0; i < 1000; i++) {
            while (true){
                System.out.println(111111);
                if (1==1){

                    return;
                }
            }
        }
        System.out.println(22222);
    }
}
