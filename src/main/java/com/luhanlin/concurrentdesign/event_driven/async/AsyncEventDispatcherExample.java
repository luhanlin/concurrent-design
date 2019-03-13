package com.luhanlin.concurrentdesign.event_driven.async;

import com.luhanlin.concurrentdesign.event_driven.Event;
import com.luhanlin.concurrentdesign.event_driven.EventDispatchExample;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.TimeUnit;

/**
 * @description: Async test
 * @author: Mr.Lu
 * @create: 2019-03-13 12:03
 **/
public class AsyncEventDispatcherExample {

    @Getter
    @AllArgsConstructor
    static class InputEvent extends Event {
        private final int x;
        private final int y;
    }

    @Getter
    @AllArgsConstructor
    static class ResultEvent extends Event {
        private final int result;
    }

    @AllArgsConstructor
    static class AsyncInputEventHandler extends AsyncChannel {

        private final AsyncEventDispatcher dispatcher;

        @Override
        protected void handle(Event message) {
            InputEvent inputEvent = (InputEvent) message;
            System.out.printf("[ %d + %d ] is running\n", inputEvent.getX(), inputEvent.getY());
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int result = inputEvent.getX() + inputEvent.getY();

            this.dispatcher.dispatch(new ResultEvent(result));
        }
    }

    static class AsyncResultEventHandler extends AsyncChannel {

        @Override
        protected void handle(Event message) {
            ResultEvent resultEvent = (ResultEvent) message;

            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The result is " + resultEvent.getResult());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AsyncEventDispatcher dispatcher = new AsyncEventDispatcher();

        dispatcher.registerChannel(InputEvent.class, new AsyncInputEventHandler(dispatcher));
        dispatcher.registerChannel(ResultEvent.class, new AsyncResultEventHandler());

        dispatcher.dispatch(new InputEvent(10, 20));
        dispatcher.dispatch(new InputEvent(22, 88));
        dispatcher.dispatch(new InputEvent(4, 12));
        dispatcher.dispatch(new InputEvent(21, 66));

//        dispatcher.shutdown();
    }
}
