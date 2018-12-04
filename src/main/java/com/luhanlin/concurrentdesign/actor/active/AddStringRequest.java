package com.luhanlin.concurrentdesign.actor.active;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/12/4 9:26 AM
 */
class AddStringRequest extends MethodRequest {

    private final String x;
    private final String y;

    public AddStringRequest(Servant servant, FutureResult<String> future, String x, String y) {
        super(servant, future);
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute() {
        Result<String> result = servant.add(x, y);
        futureResult.setResult(result);
    }
}
