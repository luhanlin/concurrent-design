package com.luhanlin.concurrentdesign.aa_thread09_classloader;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @description: 自定义类加载
 * @author: Mr.Lu
 * @create: 2019-02-22 15:24
 **/
public class MyClassLoader extends ClassLoader{

    private static final Path DEFAULT_CLASS_DIR = Paths.get("C:", "test");

    private final Path class_dir;

    protected MyClassLoader() {
        super();
        this.class_dir = DEFAULT_CLASS_DIR;
    }

    public MyClassLoader(String classDir) {
        super();
        this.class_dir = Paths.get(classDir);
    }

    public MyClassLoader(ClassLoader parent, String classDir) {
        super(parent);
        this.class_dir = Paths.get(classDir);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 加载二进制文件
        byte[] bytes = this.readClassBytes(name);

        if (bytes == null || bytes.length == 0){
            throw new ClassNotFoundException("Can not load class " + name);
        }

        // 调用class定义方法
        return this.defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] readClassBytes(String name) throws ClassNotFoundException {
        String classPath = name.replace(".", "/");
        Path classFullPath = class_dir.resolve(Paths.get(classPath + ".class"));

        System.out.println(classFullPath.toString());
        if (!classFullPath.toFile().exists()){
            throw new ClassNotFoundException("The file " + name + " is not found .");
        }

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();) {
            Files.copy(classFullPath, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();

        } catch (Exception e) {
            throw new ClassNotFoundException("Load class  " + name + " occur error .", e);
        }
    }

    @Override
    public String toString() {
        return "MyClassLoader";
    }
}
