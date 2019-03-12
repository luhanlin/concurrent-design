package com.luhanlin.concurrentdesign.bus.path_monitor;

import lombok.Data;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

/**
 * @description:
 * @author: Mr.Lu
 * @create: 2019-03-12 10:37
 **/
@Data
public class FileChangeEvent {

    private final Path path;

    private final WatchEvent.Kind<?> kind;

    public FileChangeEvent(Path path, WatchEvent.Kind<?> kind) {
        this.path = path;
        this.kind = kind;
    }
}
