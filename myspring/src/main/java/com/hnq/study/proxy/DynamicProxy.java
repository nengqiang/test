package com.hnq.study.proxy;

import java.lang.reflect.Proxy;

/**
 * @author henengqiang
 * @date 2019/12/05
 */
public abstract class DynamicProxy {

    /**
     * 维护一个目标对象
     */
    private final Object target;

    protected DynamicProxy(Object target) {
        this.target = target;
    }

    /**
     * 给目标对象生成代理对象
     */
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    preProcess();
                    Object returnVal = method.invoke(target, args);
                    afterProcess();
                    return returnVal;
                }
        );
    }

    public abstract void preProcess();

    public abstract void afterProcess();

}
