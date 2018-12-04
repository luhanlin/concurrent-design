package com.luhanlin.concurrentdesign.actor.active;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/12/3 12:06 AM
 */
abstract class MethodRequest<T> {

    protected final Servant servant;
    protected final FutureResult<T> futureResult;

    public MethodRequest(Servant servant, FutureResult result) {
        this.servant = servant;
        this.futureResult = result;
    }

    public abstract void execute();
}
