package com.luhanlin.concurrentdesign.event_driven.chat;

import com.luhanlin.concurrentdesign.event_driven.async.AsyncChannel;

/**
 * @description:
 * @author: Mr.Lu
 * @create: 2019-03-13 14:23
 **/
public class OfflineEventHandler extends AsyncChannel<OfflineEvent> {

    @Override
    protected void handle(OfflineEvent message) {
        System.out.printf("User[ %s ] is offline.\n", message.getUser().toString());
    }
}
