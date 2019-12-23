package com.hnq.practice.proxypattern.practice02.dynamicproxy;

import java.lang.reflect.Proxy;

/**
 * @author henengqiang
 * @date 2019/12/19
 */
public class ProxyFactory {

    /**
     * 维护一个目标对象
     */
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                (proxy, method, args) -> {
            System.out.println("动态代理开始...");
            Object result = method.invoke(target, args);
            System.out.println("动态代理结束");
            return result;
        });
    }
}
