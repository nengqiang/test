package com.hnq.practice.chainofresponsibilitypattern.practice01.concretehandler;

import com.hnq.practice.chainofresponsibilitypattern.practice01.handler.BaseArrearsHandler;

/**
 * PersonalUserArrearsHandler 是个人用户欠费处理器，派生于 ArrearsHandler 类。
 * 对应于职责链模式的参与者，PersonalUserArrearsHandler 是具体处理器 ConcreteHandler。
 *
 * @author henengqiang
 * @date 2019/04/24
 */
public class PersonalUserArrearsHandler extends BaseArrearsHandler {

    @Override
    public void handleArrears(String phoneNumber, double amountArrears) {
        if (amountArrears < 100) {
            System.out.println("向用户发送催款短信，欠费号码：" + phoneNumber + "，欠费金额：" + amountArrears);
        } else {
            System.out.println("向用户发送停机通知短信，欠费号码：" + phoneNumber + "，欠费金额：" + amountArrears);
            System.out.println("将号码：" + phoneNumber + "停机");
        }
    }

}
