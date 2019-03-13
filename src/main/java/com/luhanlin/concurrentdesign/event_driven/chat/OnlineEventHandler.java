package com.luhanlin.concurrentdesign.event_driven.chat;

import com.luhanlin.concurrentdesign.event_driven.async.AsyncChannel;

/**
 * @description:
 * @author: Mr.Lu
 * @create: 2019-03-13 14:23
 **/
public class OnlineEventHandler extends AsyncChannel<OnlineEvent> {

    @Override
    protected void handle(OnlineEvent message) {
        System.out.printf("User[ %s ] is online.\n", message.getUser().toString());
    }
}
