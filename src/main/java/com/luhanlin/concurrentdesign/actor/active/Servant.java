package com.luhanlin.concurrentdesign.actor.active;

import java.math.BigDecimal;

/**
 * 类详细描述： 实际执行处理的类
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/12/3 12:13 AM
 */
class Servant implements ActiveObject{
    @Override
    public Result<String> makingString(int count, char c) {
        char[] chars = new char[count];

        for (int i = 0; i < count; i++) {
            chars[i] = c;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        RealResult realResult = new RealResult(new String(chars));

        return realResult;
    }

    @Override
    public void displayString(String str) {
        System.out.println(" displayString = " + str);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Result<String> add(String x, String y) {
        BigDecimal result = new BigDecimal(x).add(new BigDecimal(y));

        return new RealResult(result + "");
    }
}
