package com.luhanlin.concurrentdesign.bus;

/**
 * @description: 异常回调接口
 * @author: Mr.Lu
 * @create: 2019-03-11 15:20
 **/
public interface EventExceptionHandler {

    void handle(Throwable throwable, EventContext context);

}
