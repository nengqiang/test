package com.hnq.practice.chainofresponsibilitypattern.practice01;

import com.hnq.practice.chainofresponsibilitypattern.practice01.client.ArrearsMgt;

/**
 * 职责链（Chain of responsibility）模式使用多个对象均有机会处理同意请求，请求在可能处理它的对象间按既定顺序流转，直至被处理。
 * 职责链将请求与实际处理它的对象解耦。职责链最常使用的场景是UI中对鼠标、键盘事件的处理。
 *
 * 1 Handler
 *  Handler是处理器接口（或抽象类），包含了各请求处理接口方法。Handler维护了Handler类型的后继请求处理器实例。
 *
 * 2 ConcreteHandler
 *  ConcreteHandler1和ConcreteHandler2是具体的处理器。它们实现了Handler接口，实现对请求的处理。在处理内部，
 * ConcreteHandler判断请求是否在自己的处理范围内，如果在则处理请求；如果不在则将请求向后继处理器传递。
 * 在实际应用中，可以在ConcreteHandler中指定其后继处理器类型，即在构造方法里实例化后继处理器；也可以提供设置
 * 后继处理器的方法由客户组织职责链。本文后面的示例采用了后一种形式。
 *
 * 3 Client
 *  Client是客户类，是职责链模式的使用者。Client实例化初始的处理器（或组织整条职责链），将请求传递给初始处理器处理。
 *
 * 场景介绍：
 *  某电信系统欠费管理模块对欠费进行处理。视欠费用户的类型不同，欠费处理形式不同。
 *
 * @author henengqiang
 * @date 2019/04/24
 */
public class Application {

    public static void main(String[] args) {
        chainOfResponsibilityTest();
    }

    private static void chainOfResponsibilityTest() {
        ArrearsMgt arrearsMgt = new ArrearsMgt();
        arrearsMgt.handleArrears();
    }
}
