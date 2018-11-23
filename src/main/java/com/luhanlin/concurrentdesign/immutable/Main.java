package com.luhanlin.concurrentdesign.immutable;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/23 3:05 PM
 */
public class Main {

    public static void main(String[] args) {
//        Person allen = new Person("Allen", 12);
//        Person bob = new Person("Bob", 22);
//
//        for (int i = 0; i < 10; i++) {
//
//            new PersonThread(allen).start();
//            new PersonThread(bob).start();
//        }

//        List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());

        List<Integer> list = new CopyOnWriteArrayList<>();
        new WriterThread(list).start();

        new ReadThread(list).start();

    }
}
