package com.hnq.study.multithread;

import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.Collection;

/**
 * 同步集合类的应用
 *
 * @author henengqiang
 * @date 2019/06/03
 */
public class ConcurrentCollectionTest {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test1() {
        Collection<User> users = Lists.newArrayList();
        users.add(new User("张三", 25));
        users.add(new User("李四", 28));
        users.add(new User("王五", 32));
        for (User user : users) {
            if ("张三".equals(user.getName())) {
                users.remove(user);
            } else {
                System.out.println(user);
            }
        }
    }

    private static void test2() {
        Collection<User> users = Lists.newCopyOnWriteArrayList();
        users.add(new User("张三", 25));
        users.add(new User("李四", 28));
        users.add(new User("王五", 32));
        for (User user : users) {
            if ("张三".equals(user.getName())) {
                users.remove(user);
            } else {
                System.out.println(user);
            }
        }
    }

    private static class User implements Cloneable {
        @Getter
        private String name;
        @Getter
        private int age;

        User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof User)) {
                return false;
            }
            User user =(User) obj;
            return (this.name.equals(user.name) && this.age == user.age);
        }

        @Override
        public int hashCode() {
            return name.hashCode() + age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            Object obj = null;
            try {
                obj = super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return obj;
        }
    }

}
