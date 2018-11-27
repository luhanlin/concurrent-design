package com.luhanlin.concurrentdesign.guarded_suspension;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/27 9:11 AM
 */
public class LivenessException extends RuntimeException {

    public LivenessException(String message) {
        super(message);
    }
}
