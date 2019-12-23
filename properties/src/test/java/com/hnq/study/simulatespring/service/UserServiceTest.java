package com.hnq.study.simulatespring.service;

import com.hnq.study.simulatespring.BeanFactory;
import com.hnq.study.simulatespring.ClassPathXmlApplicationContext;
import com.hnq.study.simulatespring.dao.IUserDao;
import com.hnq.study.simulatespring.interceptor.LogIntercept;
import com.hnq.study.simulatespring.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Proxy;

/**
 * @author henengqiang
 * @date 2019/06/18
 */
class UserServiceTest {

    @Test
    void addTest() {
        try {
            BeanFactory factory = new ClassPathXmlApplicationContext("test.xml");

            UserService userService = (UserService) factory.getBean("userService");
            IUserDao userDao = (IUserDao) factory.getBean("u");
            userService.setUserDao(userDao);
            User u = new User();
            userService.add(u);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Test
    void injectTest() {
        try {
            BeanFactory ctx = new ClassPathXmlApplicationContext("beans.xml");
            UserService userService = (UserService) ctx.getBean("userService");
            User u = new User();
            u.setUsername("u1");
            u.setPassword("p1");
            userService.add(u);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Test
    void collectionTest() {
        try {
            // 自己写的 ClassPathXmlApplicationContext 没有对集合类进行注入处理
            ApplicationContext context =
                    new org.springframework.context.support.ClassPathXmlApplicationContext("beans.xml");
            IUserDao userDao = (IUserDao) context.getBean("u");
            userDao.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void proxyTest() {
        try {
            BeanFactory factory = new ClassPathXmlApplicationContext("beans.xml");
            IUserDao userDao = (IUserDao) factory.getBean("u");
            LogIntercept logIntercept = (LogIntercept) factory.getBean("logIntercept");
            logIntercept.setTarget(userDao);
            IUserDao userDaoProxy = (IUserDao) Proxy.newProxyInstance(userDao.getClass().getClassLoader(), new Class[]{IUserDao.class}, logIntercept);
            userDaoProxy.save(new User());
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void aopAnnotationTest() {
        org.springframework.context.support.ClassPathXmlApplicationContext ctx =
                new org.springframework.context.support.ClassPathXmlApplicationContext("beans2.xml");

        UserService userService = (UserService) ctx.getBean("userService");
        User u = new User();
        u.setUsername("u1");
        u.setPassword("p1");
        userService.add(u);
        ctx.close();
    }

}