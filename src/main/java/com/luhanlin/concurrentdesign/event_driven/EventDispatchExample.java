package com.luhanlin.concurrentdesign.event_driven;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 展示示例
 * @author: Mr.Lu
 * @create: 2019-03-13 11:06
 **/
public class EventDispatchExample {

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

    static class ResultEventHandler implements Channel<ResultEvent> {

        @Override
        public void dispatch(ResultEvent message) {
            System.out.println(" The result is " + message.getResult());
        }
    }

    @AllArgsConstructor
    static class InputEventHandler implements Channel<InputEvent> {

        private final EventDispatcher dispatcher;

        @Override
        public void dispatch(InputEvent message) {
            int result = message.getX() + message.getY();

            this.dispatcher.dispatch(new ResultEvent(result));
        }
    }


    public static void main(String[] args) {
        Message inputEvent = new InputEvent(2, 4);

        EventDispatcher dispatcher = new EventDispatcher();

        dispatcher.registerChannel(InputEvent.class, new InputEventHandler(dispatcher));
        dispatcher.registerChannel(ResultEvent.class, new ResultEventHandler());

        dispatcher.dispatch(inputEvent);
    }
}
