package com.hnq.practice.proxypattern;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib-动态代理
 *
 * @author henengqiang
 * @date 2019/02/27
 */
public class MyCglibProxy implements MethodInterceptor {

    private Object target;

    /**
     * 创建代理对象
     */
    public Object getInstance(Object target) {
       this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println(method.getName() + " 方法开始之前");
        // 调用目标方法
        Object result = methodProxy.invokeSuper(methodProxy, objects);
        System.out.println(method.getName() + " 方法调用之后");
        return result;
    }
}
