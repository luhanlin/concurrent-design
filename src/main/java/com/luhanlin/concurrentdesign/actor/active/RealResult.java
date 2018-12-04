package com.luhanlin.concurrentdesign.actor.active;

/**
 * 类详细描述：实际的结果值
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/12/3 12:29 AM
 */
class RealResult<T> extends Result {

    private final T t;

    public RealResult(T t) {
        this.t = t;
    }

    @Override
    public T getResultValue() {
        return t;
    }
}
