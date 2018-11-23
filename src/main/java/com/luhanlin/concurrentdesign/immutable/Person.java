package com.luhanlin.concurrentdesign.immutable;

import lombok.Getter;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/23 4:37 PM
 */
public class Person {

    @Getter
    private final String name;
    @Getter
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "[name = "+ getName() +", age = "+ getAge() +"]";
    }
}
