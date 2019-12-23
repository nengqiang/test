package com.hnq.practice.mediatorpattern.practice01.concretemediator;

import com.hnq.practice.mediatorpattern.practice01.collegue.BaseInvChgColleague;
import com.hnq.practice.mediatorpattern.practice01.mediator.BaseInvChgMediator;

/**
 * InvChgMediator 是库存改变中介者继承类，派生于库存改变中介者抽象类 InvChgMediator。
 * InvChgMediator 实现了各库存相关方法。对应于中介者模式的参与者，InvChgMediator 是具体中介者类 ConcreteMediator。
 *
 * @author henengqiang
 * @date 2019/06/17
 */
public class InvChgMediator extends BaseInvChgMediator {

    @Override
    public int getTotal(String productName) {
        int productCount = 0;
        for (BaseInvChgColleague invChgColleague : invChgColleagues) {
            productCount += invChgColleague.getTotal(productName);
        }
        return productCount;
    }

    @Override
    public void productConsumed(String productName, int count) {
        if (getTotal(productName) >= count) {
            // 处理商品数量
            int handledProductCount = 0;
            for (BaseInvChgColleague invChgColleague : invChgColleagues) {
                handledProductCount += invChgColleague.productConsumed(productName, count - handledProductCount);
            }
        } else {
            System.out.println("库存不足，操作失败");
        }
    }

    @Override
    public void inventoryChg(String productName) {
        invChgColleagues.forEach(invChgColleague -> invChgColleague.inventoryChg(productName));
    }

}
