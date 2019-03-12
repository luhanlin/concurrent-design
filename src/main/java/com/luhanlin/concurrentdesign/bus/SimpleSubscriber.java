package com.luhanlin.concurrentdesign.bus;

/**
 * @description: 简单测试类
 * @author: Mr.Lu
 * @create: 2019-03-11 16:58
 **/
public class SimpleSubscriber {

    @Subscribe
    public void test01(String name){
        System.out.println("SimpleSubscriber name = " + name);
    }

    /**
     * @param age 参数不可以为基本数据类型，不然不能与event类型匹配
     */
    @Subscribe(topic = "test")
    public void test02(Integer age){
        System.out.println("SimpleSubscriber age = " + age);
    }
}
