package com.luhanlin.concurrentdesign.aa_thread_block05;

import java.util.concurrent.TimeUnit;

public class Metux {

    private static final Object METUX = new Object();

    public static void main(String[] args) {

        final Metux metux = new Metux();

        for (int i = 0; i < 5; i++) {
            new Thread(metux::loadResource).start();
        }

    }

    private void loadResource(){
        // 加一把锁
        synchronized (METUX){
            try {
                TimeUnit.MINUTES.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
