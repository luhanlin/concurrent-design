package com.luhanlin.concurrentdesign.event_driven.chat;

import com.luhanlin.concurrentdesign.event_driven.async.AsyncEventDispatcher;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @description:
 * @author: Mr.Lu
 * @create: 2019-03-13 14:30
 **/
public class UserChatThread extends Thread {

    private final User user;
    private final AsyncEventDispatcher dispatcher;

    public UserChatThread(User user, AsyncEventDispatcher dispatcher) {
        super(user.getName());
        this.user = user;
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {
        dispatcher.dispatch(new OnlineEvent(user));

        IntStream.range(0,10).forEach(i -> {
            dispatcher.dispatch(new ChatEvent(user, getName() + " hello-" + i));
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                dispatcher.dispatch(new OfflineEvent(user));
            }
        });
    }
}
