package com.luhanlin.concurrentdesign.event_driven.chat;

import com.luhanlin.concurrentdesign.event_driven.async.AsyncEventDispatcher;

/**
 * @description:
 * @author: Mr.Lu
 * @create: 2019-03-13 14:39
 **/
public class UserChatTest {

    public static void main(String[] args) {
        AsyncEventDispatcher dispatcher = new AsyncEventDispatcher();

        dispatcher.registerChannel(OnlineEvent.class, new OnlineEventHandler());
        dispatcher.registerChannel(OfflineEvent.class, new OfflineEventHandler());
        dispatcher.registerChannel(ChatEvent.class, new ChatEventHandler());

        new UserChatThread(new User("Lida", 11), dispatcher).start();
        new UserChatThread(new User("Tom", 21), dispatcher).start();
        new UserChatThread(new User("Warren", 18), dispatcher).start();
        new UserChatThread(new User("Allen", 33), dispatcher).start();
    }
}
