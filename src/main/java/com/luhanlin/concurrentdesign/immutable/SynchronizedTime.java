package com.luhanlin.concurrentdesign.immutable;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 类详细描述：测试synchronized耗时
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/23 5:13 PM
 */
public class SynchronizedTime {

    public static final Long CALL_COUNT = 1000000000L;

    public static void main(String[] args) {
        trail("not synch", CALL_COUNT, new NotSynch());
        trail("synch", CALL_COUNT, new Synch());
    }

    private static void trail(String msg, Long callCount, Object obj) {
        System.out.println(msg + ": start.");
        LocalDateTime now = LocalDateTime.now();

        for (int i = 0; i < callCount; i++) {
            obj.toString();
        }
        System.out.println(msg + ": end.");

        System.out.println(" Elapsed time : " + ChronoUnit.MILLIS.between(now, LocalDateTime.now()));
    }

    static class NotSynch{
        private final String name = "not synch";

        @Override
        public String toString() {
            return "[NotSynch]";
        }
    }
    static class Synch{
        private final String name = "sych";

        @Override
        public synchronized String toString() {
            return "[Synch]";
        }
    }
}
