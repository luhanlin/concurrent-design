package com.luhanlin.concurrentdesign.two_phase_termination.LRUcache;

/**
 * @description:
 * @author: Mr.Lu
 * @create: 2019-03-08 18:11
 **/
public class Reference {

    // 1M
    public final Byte[] bytes = new Byte[2 << 19];

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Reference will be GC...");
    }
}
