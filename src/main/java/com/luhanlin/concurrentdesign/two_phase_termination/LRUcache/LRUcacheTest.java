package com.luhanlin.concurrentdesign.two_phase_termination.LRUcache;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Mr.Lu
 * @create: 2019-03-08 18:13
 **/
public class LRUcacheTest {

    public static void main(String[] args) throws InterruptedException {
        LRUcache<Integer, Reference> cache = new LRUcache<>(100, key -> new Reference());

        System.out.println(cache.toString());

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            cache.get(i);

            TimeUnit.SECONDS.sleep(5);

            System.out.println("the " + i + " reference data stored at cache.");
        }
    }
}
