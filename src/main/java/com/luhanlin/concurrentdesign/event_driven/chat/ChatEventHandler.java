package com.luhanlin.concurrentdesign.event_driven.chat;

import com.luhanlin.concurrentdesign.event_driven.async.AsyncChannel;

/**
 * @description:
 * @author: Mr.Lu
 * @create: 2019-03-13 14:23
 **/
public class ChatEventHandler extends AsyncChannel<ChatEvent> {

    @Override
    protected void handle(ChatEvent message) {
        System.out.printf("User[ %s ] says : %s .\n", message.getUser().toString(), message.getMessage());
    }
}
