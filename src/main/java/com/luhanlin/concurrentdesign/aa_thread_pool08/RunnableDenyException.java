package com.luhanlin.concurrentdesign.aa_thread_pool08;

/**
 * @description:
 * @author: Mr.Lu
 * @create: 2019-02-21 11:11
 **/
public class RunnableDenyException extends RuntimeException {

    public RunnableDenyException(String msg) {
        super(msg);
    }
}
