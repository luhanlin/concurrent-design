package com.luhanlin.concurrentdesign.observer;

@FunctionalInterface
public interface Task<T> {

    T call() throws InterruptedException;
}
