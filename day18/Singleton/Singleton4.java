package org.xupt.Singleton;

/**
 * 通过静态内部类来实现
 * 通过静态内部类来创建他的唯一实例
 */
public class Singleton4 {
    private Singleton4() {

    }

    private static class Holder {

        static Singleton4 ME = new Singleton4();
    }

    public static Singleton4 getME() {
        return Holder.ME;
    }

}
