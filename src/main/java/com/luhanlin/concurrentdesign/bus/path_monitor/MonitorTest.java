package com.luhanlin.concurrentdesign.bus.path_monitor;

import com.luhanlin.concurrentdesign.bus.AsynEventBus;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description:
 * @author: Mr.Lu
 * @create: 2019-03-12 10:41
 **/
public class MonitorTest {

    @Test
    public void testPathMonitor() throws IOException {
        final AsynEventBus bus = new AsynEventBus("monitor-bus",
                (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));

        bus.register(new FileChangeListener());

        DirectoryTargetMonitor monitor = new DirectoryTargetMonitor(bus, "C:\\test");

        monitor.startMonitor();
    }
}
