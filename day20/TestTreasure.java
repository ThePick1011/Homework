package org.xupt.treasure;

import sun.management.MethodInfo;

import java.io.FileInputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class TestTreasure {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        // E:\9.22实训班共享\笔记资料
        // 类加载器, 作用：加载一个不在classpath下的类
        ClassLoader cl = new ClassLoader() {
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                try {
                    FileInputStream in = new FileInputStream("C:\\Users\\Trash\\Desktop\\Treasure.class");
                    byte[] bytes = new byte[1024 * 8];
                    int len = in.read(bytes);

                    // 调用父类的方法根据字节数组加载类
                    return defineClass(name, bytes, 0, len);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };

        Class<?> clazz = cl.loadClass("com.westos.Treasure"); // 根据类名加载类, 得到类对象
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        declaredConstructors[0].setAccessible(true);
        Object o = declaredConstructors[0].newInstance();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (int i = 1; i < declaredMethods.length - 1; i++) {
            if ((declaredMethods[i].getAnnotations() != declaredMethods[i - 1].getAnnotations()) && (declaredMethods[i - 1].getAnnotations() != declaredMethods[i].getAnnotations())) {
                declaredMethods[i].invoke(o);
                break;
            }

        }


    }
}