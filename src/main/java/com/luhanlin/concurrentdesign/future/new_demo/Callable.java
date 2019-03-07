package com.luhanlin.concurrentdesign.future.new_demo;

/**
 * @description:
 * @author: Mr.Lu
 * @create: 2019-03-07 09:48
 **/
@FunctionalInterface
public interface Callable<IN> {

    void call(IN in);
}
