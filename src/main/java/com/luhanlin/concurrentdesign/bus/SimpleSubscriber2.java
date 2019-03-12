package com.luhanlin.concurrentdesign.bus;

/**
 * @description: 简单测试类
 * @author: Mr.Lu
 * @create: 2019-03-11 16:58
 **/
public class SimpleSubscriber2 {

    @Subscribe(topic = "test")
    public void test01(String name){
        System.out.println("SimpleSubscriber2 name = " + name);
    }

    @Subscribe(topic = "test2")
    public void test02(Integer age){
        System.out.println("SimpleSubscriber2 age = " + age);
    }
}
