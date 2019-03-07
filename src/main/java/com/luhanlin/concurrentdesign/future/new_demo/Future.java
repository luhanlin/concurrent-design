package com.luhanlin.concurrentdesign.future.new_demo;

public interface Future<V> {

    /**
     * 获取返回结果
     * @return
     */
    V get() throws InterruptedException;

    /**
     * Returns {@code true} if this task completed.
     *
     * @return Returns {@code true} if this task completed.
     */
    boolean isDone();

}
