package com.luhanlin.concurrentdesign.guarded_suspension;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/23 5:56 PM
 */
public class Main {

    public static void main(String[] args) {
//        RequestQueue requestQueue = new RequestQueue();
//        new ClientThread(requestQueue,"Allen", 3141592).start();
//        new ServerThread(requestQueue,"bob", 6535897).start();

        RequestQueue input = new RequestQueue();
        RequestQueue output = new RequestQueue();

        // 先加入一条初始消息
        input.putRequest(new Request("init request "));

        new TalkThread("Allen",input,output).start();
        new TalkThread("Bella",output,input).start();
    }
}
