package com.hnq.practice.mediatorpattern.practice01.collegue;

import com.hnq.practice.mediatorpattern.practice01.mediator.BaseInvChgMediator;

/**
 * BaseInvChgColleague 是库存改变同事类。BaseInvChgColleague 是抽象类，声明了库存相关的方法并提供了默认实现。
 * 因本例中采用的是消息转发的形式，故 BaseInvChgColleague 声明的是库存相关消息处理方法的全集，同时提供了各方法的默认实现。
 * 对应于中介者模式的参与者，BaseInvChgColleague 是同事接口 Colleague。
 * 
 * @author henengqiang
 * @date 2019/06/17
 */
public abstract class BaseInvChgColleague {

    /**
     * 库存改变中介者
     */
    protected BaseInvChgMediator invChgMediator;

    public BaseInvChgColleague(BaseInvChgMediator invChgMediator) {
        this.invChgMediator = invChgMediator;
        invChgMediator.addColleague(this);
    }

    /**
     * 获取指定产品库存余量
     */
    public int getTotal(String productName) {
        return 0;
    }

    /**
     * 产品被消费
     */
    public int productConsumed(String productName, int count) {
        return 0;
    }

    /**
     * 改变库存
     */
    public void inventoryChg(String productName) {

    }
}
