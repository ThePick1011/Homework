package org.xupt.Singleton;
    /**
     * 单例设计模式
     * 通过私有化自己的构造方法来实现
     * 外界不能再调用自己的构造方法来创建新的实例化对象、
     * 局限性在于在多线程环境下仍有可能创建出多个对象
     */
public class Singleton1 {
    //将构造方法私有
    private Singleton1(){

    }
    //自己创建自己的实例化对象

    public static final Singleton1 ME = new Singleton1();

    //通过一个公共的方法返回给调用者
    public static Singleton1 getMe(){
        return ME;
    }
}
