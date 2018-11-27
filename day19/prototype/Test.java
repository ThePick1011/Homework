package org.xupt.prototype;

import java.util.Date;

public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        copy();
        deepCopy();
    }

    private static void deepCopy() throws CloneNotSupportedException {
        User2 user2 = new User2();
        user2.setAge(18);
        user2.setBirthday(new Date());
        user2.setName("ThePick");
        User2 user21 = (User2) user2.clone();
        /**
         * 返回false是因为两个对象的引用不同
         * 但是为我们可以看出birthday的变量值不同
         * 所以这样实现创建了新的实例
         * 将两个引用指向了不同的实例
         */
        System.out.println(user2 == user21);

        user21.getBirthday().setDate(28);
        System.out.println(user2.getBirthday());
        System.out.println(user21.getBirthday());
    }

    private static void copy() throws CloneNotSupportedException {
        User user = new User();
        user.setAge(18);
        user.setBirthday(new Date());
        user.setName("ThePick");
        User user1 = (User)user.clone();
        /**
         * 返回false是因为两个对象的引用不同
         * 但是为我们可以看出birthday的变量值相同
         * 所以这样实现并没有创建新的实例
         * 只是将两个引用指向了同一个对象
         */
        System.out.println(user == user1);

        user1.getBirthday().setDate(28);
        System.out.println(user.getBirthday());
        System.out.println(user1.getBirthday());
        System.out.println("**************");
    }
}
