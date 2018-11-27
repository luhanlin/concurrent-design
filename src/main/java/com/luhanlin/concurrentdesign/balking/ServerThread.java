package com.luhanlin.concurrentdesign.balking;

/**
 * 类详细描述：简易版定时保存修改信息
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/26 1:37 PM
 */
public class ServerThread extends Thread {

    private final Data data;

    public ServerThread(String name, Data data) {
        super(name);
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true){
                data.save();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
