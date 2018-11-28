package com.luhanlin.concurrentdesign.read_write_lock;

/**
 * 类详细描述：需要添加锁的数据
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/28 9:52 AM
 */
public class Data {

    private final char[] chars;
    private final ReadWriteLock lock;

    public Data(int size) {
        chars = new char[size];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = '*';
        }
        lock = new ReadWriteLock();
    }

    public char[] read() throws InterruptedException {
        lock.readLock();
        try {
            return doRead();
        } finally {
            lock.readUnlock();
        }
    }

    public void write(char c) throws InterruptedException {
        lock.writeLock();

        try {
            doWrite(c);
        } finally {
            lock.writeUnlock();
        }
    }

    private char[] doRead() {
        char[] newChars = new char[this.chars.length];
        System.arraycopy(this.chars,0, newChars,0, this.chars.length);

        slowly();
        return newChars;
    }

    private void doWrite(char c) {
        for (int i = 0; i < chars.length; i++) {
            chars[i] = c;
            slowly();
        }
    }

    private void slowly() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
