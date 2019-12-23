package com.hnq.practice.chainofresponsibilitypattern.practice01.concretehandler;

import com.hnq.practice.chainofresponsibilitypattern.practice01.handler.BaseArrearsHandler;

/**
 * CompanyUserArrearsHandler 是公司用户欠费处理器，派生于 ArrearsHandler 类。
 * 对应于职责链模式的参与者，CompanyUserArrearsHandler 是具体处理器 ConcreteHandler。
 *
 * @author henengqiang
 * @date 2019/04/24
 */
public class CompanyUserArrearsHandler extends BaseArrearsHandler {

    @Override
    public void handleArrears(String phoneNumber, double amountArrears) {
        if (isCompanyUser(phoneNumber)) {
            System.out.println("向公司财务部发送催款邮件，欠费号码：" + phoneNumber + "，欠费金额：" + amountArrears);
        } else {
            if (nextHandler != null) {
                nextHandler.handleArrears(phoneNumber, amountArrears);
            }
        }
    }

    private boolean isCompanyUser(String phoneNumber) {
        return phoneNumber.startsWith("150");
    }
}
