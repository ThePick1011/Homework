package org.xupt.prototype;

import java.io.*;
import java.util.Date;

public class User2 implements Cloneable, Serializable {

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
     *
     * @return
     * @throws CloneNotSupportedException
     * 在这里不在直接调用父类的clone()
     * 而是通过序列化流来实现
     * 这样不仅会复制出新的对象而且会创建出新的实例
     * 两个引用指向不同的实例
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            // 把自己（当前对象）写入输出流
            new ObjectOutputStream(os).writeObject(this);

            // 拿到字节数组
            byte[] bytes = os.toByteArray();

            // 反序列化为新对象
            ByteArrayInputStream is = new ByteArrayInputStream(bytes);

            // 对象输入流
            ObjectInputStream ois = new ObjectInputStream(is);

            return ois.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
