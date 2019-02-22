package com.luhanlin.concurrentdesign.aa_thread05_block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static java.lang.Thread.currentThread;

public class BooleanLock implements Lock {

    // 当前线程
    private Thread currentThread;

    // false 表示对象锁还没有被线程获取 ， true表示锁已被线程获取
    private boolean locked;

    private final List<Thread> blockedList = new ArrayList<>();

    @Override
    public void lock() throws InterruptedException {
        synchronized (this){
            while (locked){
                try {
                    // 被锁住后把线程加入阻塞队列，并进行wait
                    if (!blockedList.contains(currentThread())){
                        blockedList.add(currentThread());
                    }
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 如果没有被锁住
            blockedList.remove(currentThread());
            this.locked = true;
            this.currentThread = currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this){
            if (mills <= 0){
                lock();
            }

            long exceptMills = mills;
            long endMills = System.currentTimeMillis() + exceptMills;

            while (locked){
                if (exceptMills <= 0){
                    throw new TimeoutException("Can not get the lock during " + mills);
                }

                // 被锁住后把线程加入阻塞队列，并进行wait
                if (!blockedList.contains(currentThread())){
                    blockedList.add(currentThread());
                }
                this.wait(exceptMills);

                // 被唤醒后 重新计算剩余等待时间
                exceptMills = endMills - System.currentTimeMillis();
            }

            // 如果没有被锁住
            blockedList.remove(currentThread);
            this.locked = true;
            this.currentThread = currentThread();
        }
    }

    @Override
    public void unlock() {
        synchronized (this){
            // 锁的释放只能由当前持有锁的线程进行释放
            if (currentThread == currentThread()){ // 此种对比方式有待考虑
                this.locked = false;
                Optional.of(currentThread.getName() + " has release the lock")
                        .ifPresent(System.out::println);
                this.notifyAll();
            }
        }

    }

    @Override
    public List<Thread> getBlockedThreadList() {
        return Collections.unmodifiableList(blockedList);
    }
}
