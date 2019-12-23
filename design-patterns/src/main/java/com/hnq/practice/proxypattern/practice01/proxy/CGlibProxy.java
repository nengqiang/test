package com.hnq.practice.proxypattern.practice01.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author henengqiang
 * @date 2019/04/08
 */
public class CGlibProxy implements MethodInterceptor {

    /**
     * 代理方法
     */
    public Object createProxy(Object target) {
       // 创建一个动态对象
        Enhancer enhancer = new Enhancer();
        // 确定要增强的类，设置其父类
        enhancer.setSuperclass(target.getClass());
        // 添加回调函数
        enhancer.setCallback(this);
        // 返回创建的代理类
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("CGlib前置增强");
        Object obj = methodProxy.invokeSuper(o, objects);
        System.out.println("CGlib后置增强");
        return obj;
    }
}
