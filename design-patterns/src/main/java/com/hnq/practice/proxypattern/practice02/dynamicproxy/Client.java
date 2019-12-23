package com.hnq.practice.proxypattern.practice02.dynamicproxy;

/**
 * @author henengqiang
 * @date 2019/12/19
 */
public class Client {

    public static void main(String[] args) {
        // 创建目标对象
        ITeacherDAO target = new TeacherDAO();
        // 给目标对象创建代理对象
        ProxyFactory proxyFactory = new ProxyFactory(target);
        ITeacherDAO proxyInstance = (ITeacherDAO) proxyFactory.getProxyInstance();
        // class com.sun.proxy.$Proxy0 说明了内存中生成了动态代理对象
        System.out.println(proxyInstance.getClass());
        proxyInstance.teach();
    }

}
