package com.luhanlin.concurrentdesign.two_phase_termination.LRUcache;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * @description: 测试弱引用
 * @author: Mr.Lu
 * @create: 2019-03-11 10:04
 **/
public class GCtest {

    public static void main(String[] args) throws InterruptedException {
        Reference reference = new Reference();
        ReferenceQueue<Reference> queue = new ReferenceQueue<>();

        WeakReference<Reference> referenceWeakReference = new WeakReference<>(reference, queue);

        reference = null;
        System.out.println(referenceWeakReference.get());
        System.gc();

        TimeUnit.SECONDS.sleep(1L);

        java.lang.ref.Reference<? extends Reference> remove = queue.remove();

        System.out.println(remove.get());


    }

}
