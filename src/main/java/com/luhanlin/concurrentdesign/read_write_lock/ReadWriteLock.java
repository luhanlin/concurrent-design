package com.luhanlin.concurrentdesign.read_write_lock;

/**
 * 类详细描述：读写锁
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/28 9:41 AM
 */
public class ReadWriteLock {

    private int readingReaders = 0;     // 正在读取的线程数
    private int waitingWriters = 0;     // 正在等待写入的线程数
    private int writingWriters = 0;     // 正在写入的线程数
    private boolean preferWriter = true;   // 写入优先级，true 为优先写入

    /**
     * 读取的时候不能有正在写入的线程
     */
    public synchronized void readLock() throws InterruptedException {
        while (writingWriters > 0 || (preferWriter && waitingWriters > 0)){
            wait();
        }

        readingReaders++;
    }

    public synchronized void readUnlock(){
        readingReaders--;
        preferWriter = true;
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        waitingWriters++;
        try {
            while (readingReaders > 0 || writingWriters > 0){
                wait();
            }
        } finally {
            waitingWriters--;
        }

        writingWriters++;
    }

    public synchronized void writeUnlock(){
        writingWriters--;
        preferWriter = false;
        notifyAll();
    }

}
