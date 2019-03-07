package com.luhanlin.concurrentdesign.future.new_demo;

@FunctionalInterface
public interface Task<IN, OUT> {

    OUT get(IN in);
}
