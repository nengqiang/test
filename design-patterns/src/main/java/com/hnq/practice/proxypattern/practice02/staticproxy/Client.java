package com.hnq.practice.proxypattern.practice02.staticproxy;

/**
 * @author henengqiang
 * @date 2019/12/19
 */
public class Client {

    public static void main(String[] args) {
        // 创建目标对象
        TeacherDAO teacherDAO = new TeacherDAO();
        // 创建代理对象，并把目标对象传入
        TeacherDAOProxy teacherDAOProxy = new TeacherDAOProxy(teacherDAO);
        teacherDAOProxy.teach();
    }

}
