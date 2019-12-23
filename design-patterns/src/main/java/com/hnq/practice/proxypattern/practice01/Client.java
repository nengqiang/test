package com.hnq.practice.proxypattern.practice01;

import com.hnq.practice.proxypattern.practice01.impl.TargetClassImpl;
import com.hnq.practice.proxypattern.practice01.proxy.CGlibProxy;
import com.hnq.practice.proxypattern.practice01.proxy.JdkProxy;

/**
 * @author henengqiang
 * @date 2019/04/08
 */
public class Client {

    public static void main(String[] args) {
        jdkProxy();
        cglibProxy();
    }

    private static void jdkProxy() {
        JdkProxy jdkProxy = new JdkProxy();
        ITargetClass targetClass = new TargetClassImpl();
        ITargetClass targetClass1 = (ITargetClass) jdkProxy.createProxy(targetClass);
        targetClass1.sayHello();
    }

    private static void cglibProxy() {
        CGlibProxy cGlibProxy = new CGlibProxy();
        CGlibTargetClass cGlibTargetClass = new CGlibTargetClass();
        CGlibTargetClass cGlibTargetClass1 = (CGlibTargetClass) cGlibProxy.createProxy(cGlibTargetClass);
        cGlibTargetClass1.sayHello();
    }

}
