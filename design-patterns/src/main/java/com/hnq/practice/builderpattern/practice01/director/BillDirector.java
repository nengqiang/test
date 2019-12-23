package com.hnq.practice.builderpattern.practice01.director;

import com.hnq.practice.builderpattern.practice01.abstractbuilder.IBillBuilder;
import com.hnq.practice.builderpattern.practice01.management.Order;

import java.util.Calendar;

/**
 * BillDirector 是账单导向器类，用于控制产品（账单）的构建过程。对应于生成器模式的参与者，BillDirector 是导向器。
 *
 * @author henengqiang
 * @date 2019/03/08
 */
public class BillDirector {

    /**
     * 账单生成器
     */
    private IBillBuilder billBuilder;

    public BillDirector(IBillBuilder billBuilder) {
        this.billBuilder = billBuilder;
    }

    public void constructBill(Order order) {
        billBuilder.buildPrice(order.getUnitPrice());
        // vip有折扣
        if (order.getVIP()) {
            billBuilder.buildDiscount();
        }
        billBuilder.buildTax();
        // 会员日全场免邮
        int membersDay = 28;
        if (!isTodayTheGivenDay(membersDay, Calendar.DAY_OF_MONTH)) {
            billBuilder.buildFreight();
        }
    }

    private boolean isTodayTheGivenDay(int theDay, int field) {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(field) == theDay;
    }
}
