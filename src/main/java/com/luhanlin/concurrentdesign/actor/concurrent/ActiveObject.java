package com.luhanlin.concurrentdesign.actor.concurrent;

import java.util.concurrent.Future;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/12/3 1:18 AM
 */
public interface ActiveObject {

    Future<String> makingString(int count, char c);

    void displayString(String str);

    Future<String> add(String x, String y);

    void shutdown();
}
