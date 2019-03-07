package com.luhanlin.concurrentdesign.future.new_demo;

/**
 * @description: future 直接返回的对象
 * @author: Mr.Lu
 * @create: 2019-03-07 09:52
 **/
public class FutureData<V> implements Future<V>{

    private V result;

    private boolean isDone = false;

    private static final Object LOCK = new Object();

    @Override
    public V get() throws InterruptedException {
        synchronized (LOCK){
            while (!isDone()){
                LOCK.wait();
            }
            return result;
        }
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    protected void finish(V result){
        synchronized (LOCK){
            if (isDone){
                return;
            }
            this.result = result;
            this.isDone = true;
            LOCK.notifyAll();
        }
    }
}
