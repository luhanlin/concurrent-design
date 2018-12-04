package com.luhanlin.concurrentdesign.actor.active;

/**
 * 类详细描述：执行方法调用总接口
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/12/2 11:19 PM
 */
public interface ActiveObject {

    Result<String> makingString(int count, char c);

    void displayString(String str);

    Result<String> add(String x, String y);
}
