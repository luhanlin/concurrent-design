package com.luhanlin.concurrentdesign.aa_thread09_classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description: 类加载访问静态变量测试
 * @author: Mr.Lu
 * @create: 2019-02-22 14:01
 **/
public class ClassLoaderTest01 {

    // 第一处  x = 1  y = 1
//    private static ClassLoaderTest01 instance = new ClassLoaderTest01();

    private static int x = 1;

    // 第二处  x = 2  y = 1
    private static ClassLoaderTest01 instance = new ClassLoaderTest01();

    private static int y;

    // 第三处  x = 2  y = 1
//    private static ClassLoaderTest01 instance = new ClassLoaderTest01();

    public ClassLoaderTest01() {
        x++;
        y++;
    }

    public static ClassLoaderTest01 getInstance(){
        return instance;
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
//        ClassLoaderTest01 instance = ClassLoaderTest01.getInstance();
//        System.out.println("x = " + instance.x);
//        System.out.println("y = " + instance.y);

//        System.out.println(System.getProperty("sun.boot.class.path"));
//        System.out.println(System.getProperty("java.ext.dirs"));
//        System.out.println(System.getProperty("java..class.path"));
//        System.out.println(ClassLoaderTest01.class.getClassLoader());

        /** 测试{@link MyClassLoader } */
        MyClassLoader myClassLoader = new MyClassLoader(null, "C:\\Users\\UCxxx\\Desktop\\luhanlin\\my-git\\concurrent-design\\target\\classes");
//        MyClassLoader myClassLoader = new MyClassLoader(null, "C://test");

        // 将 Hello world java 类放到C://test 进行编译
        Class<?> aClass = myClassLoader.findClass("com.luhanlin.concurrentdesign.aa_thread09_classloader.HelloWorldBak");
//        Class<?> aClass = myClassLoader.findClass("HelloWorld");

        System.out.println(aClass.getClassLoader());

        Object o = aClass.newInstance();
        System.out.println(o);

        Method hello = aClass.getMethod("hello");
        String result = (String) hello.invoke(o);
        System.out.println(result);

    }
}
