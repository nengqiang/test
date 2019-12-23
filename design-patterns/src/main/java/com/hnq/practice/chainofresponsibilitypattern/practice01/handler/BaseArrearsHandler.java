package com.hnq.practice.chainofresponsibilitypattern.practice01.handler;

/**
 * ArrearsHandler 是欠费处理器接口，声明了处理欠费方法 handleArrears。对应于职责链模式的参与者，ArrearsHandler 是处理器接口 Handler。
 *
 * @author henengqiang
 * @date 2019/04/24
 */
public abstract class BaseArrearsHandler {

    /**
     * 后继处理器
     */
    protected BaseArrearsHandler nextHandler = null;

    public void setNextHandler(BaseArrearsHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleArrears(String phoneNumber, double amountArrears);

}
