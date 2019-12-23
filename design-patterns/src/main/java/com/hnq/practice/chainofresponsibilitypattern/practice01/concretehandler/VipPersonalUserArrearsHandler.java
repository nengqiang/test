package com.hnq.practice.chainofresponsibilitypattern.practice01.concretehandler;

import com.hnq.practice.chainofresponsibilitypattern.practice01.handler.BaseArrearsHandler;

/**
 * VipPersonalUserArrearsHandler 是VIP个人用户欠费处理器，派生于 ArrearsHandler 类。
 * 对应于职责链模式的参与者，VipPersonalUserArrearsHandler 是具体处理器 ConcreteHandler。
 *
 * @author henengqiang
 * @date 2019/04/24
 */
public class VipPersonalUserArrearsHandler extends BaseArrearsHandler {

    @Override
    public void handleArrears(String phoneNumber, double amountArrears) {
        if (isVip(phoneNumber)) {
            System.out.println("向VIP用户发送催款短信，欠费号码：" + phoneNumber + "，欠费金额：" + amountArrears);
        } else {
            if (nextHandler != null) {
                nextHandler.handleArrears(phoneNumber, amountArrears);
            }
        }
    }

    private boolean isVip(String phoneNumber) {
        return phoneNumber.endsWith("8888");
    }
}
