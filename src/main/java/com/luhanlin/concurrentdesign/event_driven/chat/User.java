package com.luhanlin.concurrentdesign.event_driven.chat;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @description:
 * @author: Mr.Lu
 * @create: 2019-03-13 14:21
 **/
@Data
@AllArgsConstructor
public class User {

    private String name;
    private Integer age;
}
