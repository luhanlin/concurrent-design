package com.luhanlin.concurrentdesign.actor;

import com.luhanlin.concurrentdesign.actor.active.ActiveObject;
import com.luhanlin.concurrentdesign.actor.active.ActiveObjectFactory;

/**
 * 类详细描述：主程序
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/12/2 11:16 PM
 */
public class Main {

    public static void main(String[] args) {

        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();

//        new MakingClientThread("Alice",activeObject).start();
//        new MakingClientThread("Bella",activeObject).start();
//        new DisplayClientThread("John",activeObject).start();
        new AddClientThread("Bella", activeObject).start();

    }
}
