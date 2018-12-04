package com.luhanlin.concurrentdesign.actor.active;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/12/3 12:10 AM
 */
class MakingStringRequest extends MethodRequest{

    private final int count;
    private final char c;


    public MakingStringRequest(Servant servant, FutureResult<String> future, int count, char c) {
        super(servant, future);
        this.count = count;
        this.c = c;
    }

    @Override
    public void execute() {
        Result<String> result = servant.makingString(count, c);
        futureResult.setResult(result);
    }
}
