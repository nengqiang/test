package com.hnq.practice.builderpattern.practice01.concretebuilder;

import com.hnq.practice.builderpattern.practice01.abstractbuilder.IBillBuilder;
import com.hnq.practice.builderpattern.practice01.concreteproduct.InternationalBill;

import java.util.Calendar;

/**
 * InternationalBillBuilder 是国际账单生成器类，实现了 IBillBuilder 接口，生成的产品是 InternationalBill 类对象。
 * 对应于生成器模式的参与者，InternationalBillBuilder 是具体生成器。
 *
 * @author henengqiang
 * @date 2019/03/08
 */
public class InternationalBillBuilder implements IBillBuilder {

    /**
     * 国际账单
     */
    private InternationalBill internationalBill = new InternationalBill();

    @Override
    public void buildPrice(float basicPrice) {
        internationalBill.setBasicPrice(basicPrice);
        internationalBill.setTotal(internationalBill.getTotal() + internationalBill.getBasicPrice());
    }

    @Override
    public void buildTax() {
        // 大于起征点
        float markingPointPrice = 50;
        float point = 0.2f;
        if (internationalBill.getTotal() * point > markingPointPrice) {
            internationalBill.setTax(internationalBill.getTotal() * point - markingPointPrice);
        }
        internationalBill.setTotal(internationalBill.getTotal() + internationalBill.getTax());
    }

    @Override
    public void buildFreight() {
        internationalBill.setFreight(150);
        internationalBill.setTotal(internationalBill.getTotal() + internationalBill.getFreight());
    }

    @Override
    public void buildDiscount() {
        // 周五全场八折，平时九折
        if (isTodayTheGivenDay(Calendar.FRIDAY, Calendar.DAY_OF_WEEK)) {
            internationalBill.setDiscountRate(0.2f);
        } else {
            internationalBill.setDiscountRate(0.1f);
        }
        internationalBill.setTotal(internationalBill.getTotal() * (1 - internationalBill.getDiscountRate()));
    }

    @Override
    public Object getBill() {
        return internationalBill;
    }
}
