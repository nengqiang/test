package com.hnq.study.simulatespring.interceptor;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * AOP
 *
 * 实现动态代理的接口InvocationHandler
 * 然后重写invoke方法。
 *
 * @author henengqiang
 * @date 2019/06/26
 */
@Aspect
@Component
public class LogIntercept implements InvocationHandler {

    private Object target;

    public void beforeMethod() {
        System.out.println("Save start...");
    }

    public void afterMethod() {
        System.out.println("Save end...");
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    /*
     * AOP的动态代理实现
     */

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        beforeMethod();
        method.invoke(target, args);
        afterMethod();
        return null;
    }

    /*
     * AOP的annotation实现
     */

    @Before("execution(public void com.hnq.study.simulatespring.dao.impl.UserDaoImpl.save(com.hnq.study.simulatespring.model.User))")
    public void before() {
        System.out.println("Method Start...");
    }

    @After("execution(public void com.hnq.study.simulatespring.dao.impl.UserDaoImpl.save(com.hnq.study.simulatespring.model.User))")
    public void after() {
        System.out.println("Method finished.");
    }
}
