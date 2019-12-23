package com.hnq.practice.proxypattern;

/**
 * @author henengqiang
 * @date 2019/02/27
 */
public class ProxyMain {

    public static void main(String[] args) {
        useOfCglibProxy();
        useOfJdkProxy();
    }

    // TODO: 2019-02-27 henengqiang: What's the problem?

    private static void useOfCglibProxy() {
        // cglib 动态代理使用
        MyMethod proxyMethod = (MyMethod) new MyCglibProxy().getInstance(new MyMethod());
        proxyMethod.sing();
        proxyMethod.song("<以后的以后>");
    }

    private static void useOfJdkProxy() {
        MyMethod myMethod = new MyMethod();
        MyMethod proxyMethod = (MyMethod) new MyDynamicProxy().bind(myMethod);
        proxyMethod.go();
        proxyMethod.back("HAHA");
    }

}
