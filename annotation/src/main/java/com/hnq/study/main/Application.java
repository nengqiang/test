package com.hnq.study.main;

import com.hnq.study.check.UserCheck;
import com.hnq.study.factory.UserFactory;
import com.hnq.study.model.User;

/**
 * @author henengqiang
 * @date 2019/03/07
 */
public class Application {

    public static void main(String[] args) {
        testInitAnnotation();
        testValidateAnnotation();
    }

    private static void testInitAnnotation() {
        User user = null;
        System.out.println(checkUserNull(user));

        user = new User("Bob", "male");
        System.out.println(checkUserNull(user));
    }

    private static User checkUserNull(User user) {
        if (user == null) {
            return UserFactory.create();
        }
        return user;
    }


    private static void testValidateAnnotation() {
        User user = new User();
        user.setName("Candy");
        user.setGender("female");
        System.out.println(UserCheck.check(user));

        User u = new User();
        u.setName("Hamburger");
        u.setGender("male");
        System.out.println(UserCheck.check(u));

        User us = new User();
        us.setName("奥巴马");
        us.setGender("男");
        System.out.println(UserCheck.check(us));
    }

}
