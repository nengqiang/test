package com.hnq.study.threadlocal;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author henengqiang
 * @date 2020/4/22
 */
public class ThreadLocalUse {

    private static final ThreadLocal<User> threadLocalUser = new ThreadLocal<>();

    public static void main(String[] args) {
        User user = new User("Alice", 18, "15166666666");
        processUser(user);
    }

    private static void processUser(User user) {
        try {
            threadLocalUser.set(user);
            print();
            modify();
            check();
        } finally {
            threadLocalUser.remove();
        }
    }

    private static void print() {
        User u = threadLocalUser.get();
        System.out.println(u);
    }

    private static void modify() {
        User u = threadLocalUser.get();
        u.setUsername("Bob");
        u.setAge(30);
        u.setPhone("15155555555");
    }

    private static void check() {
        User u = threadLocalUser.get();
        if ("Bob".equals(u.getUsername())) {
            System.out.println("Modify Success!");
            System.out.println(u);
        }
    }

    @Data
    @AllArgsConstructor
    private static class User {
        private String username;
        private Integer age;
        private String phone;
    }

}
