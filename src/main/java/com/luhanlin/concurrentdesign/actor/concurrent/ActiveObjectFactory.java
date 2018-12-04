package com.luhanlin.concurrentdesign.actor.concurrent;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/12/3 1:18 AM
 */
public class ActiveObjectFactory {

    public static ActiveObject createActiveObject() {
        return new ActiveObjectImpl();
    }
}
