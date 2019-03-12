package com.luhanlin.concurrentdesign.bus.path_monitor;

import com.luhanlin.concurrentdesign.bus.Subscribe;

/**
 * @description: 目录监控订阅者
 * @author: Mr.Lu
 * @create: 2019-03-12 10:39
 **/
public class FileChangeListener {

    @Subscribe
    public void onChange(FileChangeEvent event){
        System.out.printf("%s - %s \n", event.getPath(), event.getKind());
    }
}
