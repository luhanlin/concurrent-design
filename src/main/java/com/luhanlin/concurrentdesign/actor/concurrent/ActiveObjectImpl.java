package com.luhanlin.concurrentdesign.actor.concurrent;

import java.math.BigDecimal;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/12/3 1:19 AM
 */
public class ActiveObjectImpl implements ActiveObject {

    private final ExecutorService service = Executors.newSingleThreadExecutor();

    @Override
    public Future<String> makingString(int count, char c) {

        Future<String> future = service.submit(new MakingStrRequest(count, c));

        return future;
    }

    @Override
    public void displayString(String str) {
        service.execute(new DisplayStringThread(str));
    }

    @Override
    public Future<String> add(String x, String y) {
        Future<String> future = service.submit(new AddRequest(x, y));
        return future;
    }

    @Override
    public void shutdown() {
        service.shutdown();
    }

    private class MakingStrRequest implements Callable<String> {
        private int count;
        private char c;

        public MakingStrRequest(int count, char c) {
            this.c = c;
            this.count = count;
        }

        @Override
        public String call() throws Exception {
            char[] chars = new char[count];

            for (int i = 0; i < count; i++) {
                chars[i] = c;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return new String(chars);
        }
    }

    private class DisplayStringThread implements Runnable {

        private String str;

        public DisplayStringThread(String str) {
            this.str = str;
        }

        @Override
        public void run() {
            System.out.println(" displayString = " + str);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class AddRequest implements Callable<String> {

        private String x;
        private String y;

        public AddRequest(String x, String y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String call() throws Exception {
            System.out.println("开始计算");
            return String.valueOf(new BigDecimal(x).add(new BigDecimal(y)));
        }
    }
}
