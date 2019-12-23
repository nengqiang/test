package com.hnq.practice.proxypattern.practice01.impl;

import com.hnq.practice.proxypattern.practice01.ITargetClass;

/**
 * @author henengqiang
 * @date 2019/04/08
 */
public class TargetClassImpl implements ITargetClass {

    @Override
    public void sayHello() {
        System.out.println("您好！");
    }

}
