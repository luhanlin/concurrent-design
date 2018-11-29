package com.luhanlin.concurrentdesign.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/29 9:39 AM
 */
public class FutureData extends FutureTask<Data> implements Data {

    private Data data;
    private boolean ready;

    public FutureData(Callable<Data> callable) {
        super(callable);
    }

    @Override
    public String getContent() {
        String content = null;

        try {
            content = get().getContent();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return content;
    }

//    public synchronized void setData(Data data){
//        if (ready){
//            return;     // balk
//        }
//
//        this.data = data;
//        this.ready = true;
//        notifyAll();
//    }

//    @Override
//    public synchronized String getContent() {
//        while (!ready){
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        return data.getContent();
//    }


}
