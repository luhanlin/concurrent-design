package com.luhanlin.concurrentdesign.actor.active;

/**
 * 类详细描述：Future 模式的返回值
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/12/2 11:49 PM
 */
class FutureResult<T> extends Result{

    private Result<T> result;
    private boolean ready = false;

    @Override
    public synchronized T getResultValue() {
        while (!ready){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result.getResultValue();
    }

    public synchronized void setResult(Result<T> result) {
        this.result = result;
        this.ready = true;
        notifyAll();
    }
}
