package com.luhanlin.concurrentdesign.event_driven.chat;

import com.luhanlin.concurrentdesign.event_driven.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 在线event
 * @author: Mr.Lu
 * @create: 2019-03-13 14:19
 **/
@Getter
@AllArgsConstructor
public class OnlineEvent extends Event {
    private final User user;
}
