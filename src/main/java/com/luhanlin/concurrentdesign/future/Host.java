package com.luhanlin.concurrentdesign.future;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/28 2:43 PM
 */
public class Host {

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    public Data request(final int count, final char c){
        System.out.println("        request (" + count + " , " + c + ") Begin");

        final FutureData futureData = new FutureData(() -> new RealData(count, c));

        new Thread(futureData).start();

        System.out.println("        request (" + count + " , " + c + ") end");

        return futureData;
    }

}
