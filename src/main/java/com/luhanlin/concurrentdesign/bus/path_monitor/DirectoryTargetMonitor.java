package com.luhanlin.concurrentdesign.bus.path_monitor;

import com.luhanlin.concurrentdesign.bus.Bus;

import java.io.IOException;
import java.nio.file.*;

/**
 * @description: 服务目录监控
 * @author: Mr.Lu
 * @create: 2019-03-12 10:20
 **/
public class DirectoryTargetMonitor {

    private WatchService watchService;

    // 消息总线
    private final Bus bus;

    // 监控目录
    private final Path path;

    private volatile boolean running = false;

    public DirectoryTargetMonitor(final Bus bus, final String targetPath, String... morePaths) {
        this.bus = bus;
        this.path = Paths.get(targetPath, morePaths);
    }

    public DirectoryTargetMonitor(final Bus bus, final String targetPath) {
        this(bus, targetPath, "");
    }

    public void startMonitor() throws IOException {
        this.watchService = FileSystems.getDefault().newWatchService();

        this.path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.OVERFLOW);

        System.out.printf("The directory[%s] is monitoring .\n", path);

        this.running = true;

        while(running){
            WatchKey watchKey = null;
            try {
                watchKey = watchService.take();
                watchKey.pollEvents().forEach(watchEvent -> {
                    WatchEvent.Kind<?> kind = watchEvent.kind();
                    Path context = (Path) watchEvent.context();
                    Path child = this.path.resolve(context);

                    this.bus.post(new FileChangeEvent(child, kind));
                });

            } catch (InterruptedException e) {
                this.running = false;
            } finally {
                if (watchKey != null) {
                    watchKey.reset();
                }
            }
        }
    }


    public void stopMonitor() throws IOException {
        System.out.printf("The directory[%s] monitor will be stop .\n", path);
        Thread.currentThread().interrupt();
        this.running = false;
        this.watchService.close();
        System.out.printf("The directory[%s] monitor will be stop done.\n", path);
    }
}
