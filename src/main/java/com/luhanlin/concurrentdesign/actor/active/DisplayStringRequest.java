package com.luhanlin.concurrentdesign.actor.active;

/**
 * 类详细描述：显示字符的请求封装
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/12/3 12:19 AM
 */
class DisplayStringRequest extends MethodRequest {

    private final String str;

    public DisplayStringRequest(Servant servant, String str) {
        super(servant, null);
        this.str = str;
    }

    @Override
    public void execute() {
        servant.displayString(str);
    }
}
