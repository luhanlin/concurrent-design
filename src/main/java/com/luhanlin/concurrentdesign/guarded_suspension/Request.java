package com.luhanlin.concurrentdesign.guarded_suspension;

import lombok.Data;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/23 5:58 PM
 */
@Data
public class Request {

    private String name;

    @Override
    public String toString() {
        return "[Request : " + name + " ]";
    }

    public Request(String name) {
        this.name = name;
    }
}
