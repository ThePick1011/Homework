package org.xupt.Singleton;

public class Test {
    public static void main(String[] args) {
        System.out.println("通过私有化构造器");
        Singleton1 singleton1 = Singleton1.getMe();
        Singleton1 singleton11 = Singleton1.getMe();
        System.out.println(singleton1 == singleton11);
        System.out.println("***********************");
        System.out.println("通过枚举实现");
        Singleton3 singleton3 = Singleton3.Me;
        Singleton3 singleton31 = Singleton3.Me;
        System.out.println(singleton3 == singleton31);
        System.out.println("***********************");
        System.out.println("通过静态内部类实现");
        Singleton4 singleton4 = Singleton4.getME();
        Singleton4 singleton41 = Singleton4.getME();
        System.out.println(singleton4 == singleton41);
        System.out.println("***********************");
    }
}
