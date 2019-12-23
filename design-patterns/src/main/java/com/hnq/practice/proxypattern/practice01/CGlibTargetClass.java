package com.hnq.practice.proxypattern.practice01;

/**
 * @author henengqiang
 * @date 2019/04/08
 */
public class CGlibTargetClass {

    public void sayHello() {
        System.out.println("我是CGlib，我不需要接口");
    }

}
