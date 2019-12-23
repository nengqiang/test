package com.hnq.practice.mediatorpattern.practice01.mediator;

import com.google.common.collect.Lists;
import com.hnq.practice.mediatorpattern.practice01.collegue.BaseInvChgColleague;

import java.util.List;

/**
 * BaseInvChgMediator 是库存改变中介者类。BaseInvChgMediator 声明了维护同事列表的相应方法及库存改变相关的事件通知接口。
 * 对应于中介者模式的参与者，BaseInvChgMediator 是中介者接口 Mediator。
 * 
 * @author henengqiang
 * @date 2019/06/17
 */
public abstract class BaseInvChgMediator {

    /**
     * 库存改变同事集合
     */
    protected List<BaseInvChgColleague> invChgColleagues = Lists.newArrayList();

    /**
     * 获取指定产品的库存余量
     */
    public abstract int getTotal(String productName);

    /**
     * 产品被消费
     * @param productName   产品名
     * @param count         消费数量
     */
    public abstract void productConsumed(String productName, int count);

    /**
     * 库存改变
     */
    public abstract void inventoryChg(String productName);

    public void addColleague(BaseInvChgColleague invChgColleague) {
        invChgColleagues.add(invChgColleague);
    }

    public void removeColleague(BaseInvChgColleague invChgColleague) {
        invChgColleagues.remove(invChgColleague);
    }
}
