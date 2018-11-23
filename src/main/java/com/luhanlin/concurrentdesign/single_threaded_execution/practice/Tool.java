package com.luhanlin.concurrentdesign.single_threaded_execution.practice;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/23 11:02 AM
 */
public class Tool {

    private String name;

    public Tool(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "["+ name +"]";
    }
}
