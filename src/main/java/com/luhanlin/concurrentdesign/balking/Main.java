package com.luhanlin.concurrentdesign.balking;

import java.util.concurrent.TimeoutException;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/26 1:43 PM
 */
public class Main {

    public static void main(String[] args) {
//        Data data = new Data("test", "Hello");
//
//        new ServerThread("Allen",data).start();
//        new ChangerThread("Bella",data).start();

        Hosts hosts = new Hosts(10000);

        try {
            hosts.execute();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
