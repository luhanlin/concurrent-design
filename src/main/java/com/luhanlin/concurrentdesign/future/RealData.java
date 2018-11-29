package com.luhanlin.concurrentdesign.future;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2018/11/29 9:49 AM
 */
public class RealData implements Data {

    private final String content;

    public RealData(int count, char c) {
        System.out.println("        Making realData (" + count + ", " + c + ") begin");

        StringBuffer stringBuffer = new StringBuffer(count);
        for (int i = 0; i < count; i++) {
            stringBuffer.append(c);
            slowly();
        }
        content = new String(stringBuffer);

        System.out.println("        Making realData (" + count + ", " + c + ") end ");

    }

    private void slowly() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getContent() {
        return content;
    }
}
