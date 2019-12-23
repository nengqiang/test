package com.hnq.practice.proxypattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 *
 * @author henengqiang
 * @date 2019/02/27
 */
public class MyDynamicProxy implements InvocationHandler {

    /**
     * 需要代理的目标对象
     */
    private Object target;

    /**
     * 写法固定，aop专用：绑定委托对象并返回一个代理类
     */
    public Object bind(Object target) {
       this.target = target;
       return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    /**
     * @param proxy     被代理的对象
     * @param method    要调用的方法
     * @param args      方法调用时所需要的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName() + " 执行之前");
        // 执行目标方法
        Object result = method.invoke(target, args);
        System.out.println(method.getName() + " 执行之后");
        return result;
    }
}
