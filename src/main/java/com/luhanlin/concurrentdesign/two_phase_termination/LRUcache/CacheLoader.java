package com.luhanlin.concurrentdesign.two_phase_termination.LRUcache;

/**
 * @description:
 * @author: Mr.Lu
 * @create: 2019-03-08 17:38
 **/
@FunctionalInterface
public interface CacheLoader<K, V> {

    V produce(K k);
}
