package com.luhanlin.concurrentdesign.read_write_lock.reentrant;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/28 11:52 AM
 */
public class Database <K, V>{

    private final Map<K, V> map = new HashMap();

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true/* false */);
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    public void clear() {
        writeLock.lock();

        try {
            verySlowly();
            map.clear();
        } finally {
            writeLock.unlock();
        }
    }

    // 使用锁
    public void assign(K key, V value){
        writeLock.lock();

        try {
            verySlowly();
            map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    // 使用 synchronized 程序执行结果错误
//    public synchronized void assign(K key, V value){
//        verySlowly();
//        map.put(key, value);
//    }

    public V retrive(K key){
        readLock.lock();

        try {
            slowly();
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }


//    public synchronized V retrive(K key){
//        slowly();
//        return map.get(key);
//    }

    private void slowly() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void verySlowly() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
