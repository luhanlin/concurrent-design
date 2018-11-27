package com.luhanlin.concurrentdesign.balking;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/26 1:32 PM
 */
public class Data {

    private String fileName;;
    private String context;
    private Boolean changed;

    public Data(String fileName, String context) {
        this.fileName = fileName;
        this.context = context;
        this.changed = true;
    }

    public synchronized void change(String context){
        this.context = context;
        this.changed = true;
    }

    public synchronized void save(){
        // 此处为balk 控制
        if (!changed){
            System.out.println(Thread.currentThread().getName() + " no changed !");
            return;
        }

        // 真实调用save方法
        doSave();
        changed = false;
    }

    private void doSave() {
        System.out.println(Thread.currentThread().getName() + " calls doSave, context = " + context);

        // TODO 此处可以保存到文件中
    }
}
