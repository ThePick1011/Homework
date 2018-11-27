package org.xupt.Singleton;

/**
 * 同样的方法实现一个单例
 * 但是考虑到多线程环境一次我们给get方法加上同步
 * 当该对象的实例已经存在时我们就不再创建
 */
public class Singleton2 {
    //将构造方法私有
    private Singleton2(){

    }
    //自己创建自己的实例化对象

    public static Singleton2 ME = null;

    //通过一个公共的方法返回给调用者
    public static  synchronized Singleton2 getMe(){
        if(ME ==null){
            ME = new Singleton2();
        }
        return ME;
    }
}
