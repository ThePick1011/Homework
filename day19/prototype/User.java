package org.xupt.prototype;

/**
 * 所谓原型模式就是用原型实例指定创建对象的种类，
 * 并且通过复制这些原型创建新的对象。
 */
import java.util.Date;

public class User implements Cloneable {

    private String name;
    private int age;
    private Date birthday;



    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    /**
     *  重写接口方法
     *  但是这样的写法只是浅拷贝
     *  也就是说只是将两个引用指向了同一个对象并没有创建新的实例
     */

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

