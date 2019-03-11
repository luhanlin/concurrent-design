package com.luhanlin.concurrentdesign.two_phase_termination.LRUcache;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 如果一个数据在最近一段时间没有被访问到，那么在将来它被访问的可能性也很小。也就是说，当限定的空间已存满数据时，
 *              应当把最久没有被访问到的数据淘汰
 * @author: Mr.Lu
 * @create: 2019-03-08 17:27
 **/
public class LRUcache<K, V> {

    // 空间容量大小
    private final int capacity;

    private final LinkedList<K> keyList = new LinkedList<>();

    private final HashMap<K, SoftReference<V>> cache = new HashMap<>();

    private final CacheLoader<K, V> loader;

    public LRUcache(int capacity, CacheLoader<K, V> loader) {
        this.capacity = capacity;
        this.loader = loader;
    }

    public void put(K k, V v){
        //1. 如果超出限定容量
        if (keyList.size() >= capacity) {
            K key = keyList.removeFirst();
            cache.remove(key);
        }

        // 2. 如果 keylist 中包含key，则移除后重新放到尾部
        keyList.remove(k);
        keyList.addLast(k);

        cache.put(k, new SoftReference<>(v));
    }

    public V get(K key){
        V value;

        boolean success = keyList.remove(key);
        if (!success) {
            // if fail , we will get value from cacheLoader.
            value = loader.produce(key);

            this.put(key, value);
        } else {
            // if success, we will get value from cache.
            value = cache.get(key).get();
            keyList.addLast(key);
        }
        return value;
    }

    @Override
    public String toString() {
        return this.keyList.toString();
    }
}
