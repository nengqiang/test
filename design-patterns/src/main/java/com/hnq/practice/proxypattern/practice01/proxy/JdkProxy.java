package com.hnq.practice.proxypattern.practice01.proxy;

import com.hnq.practice.proxypattern.practice01.ITargetClass;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author henengqiang
 * @date 2019/04/08
 */
public class JdkProxy implements InvocationHandler {

    private ITargetClass targetClass;

    public Object createProxy(ITargetClass targetClass) {
        this.targetClass = targetClass;
        // 获取本类类加载器
        ClassLoader classLoader = JdkProxy.class.getClassLoader();
        // 获取被代理对象的所有接口
        Class[] clazz = targetClass.getClass().getInterfaces();
        // 生成代理类并返回
        return Proxy.newProxyInstance(classLoader, clazz, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JdkProxy前置增强");
        Object obj = method.invoke(targetClass, args);
        System.out.println("JdkProxy后置增强");
        return obj;
    }

}
