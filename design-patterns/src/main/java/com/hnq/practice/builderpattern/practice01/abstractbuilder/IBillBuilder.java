package com.hnq.practice.builderpattern.practice01.abstractbuilder;

import java.util.Calendar;

/**
 * IBillBuilder 是账单生成器接口。对应于生成器模式的参与者，IBillBuilder 是抽象生成器。
 *
 * @author henengqiang
 * @date 2019/03/08
 */
public interface IBillBuilder {

    /**
     * 生成产品价格
     * @param basicPrice    基础价格
     */
    void buildPrice(float basicPrice);

    /**
     * 生成税费
     */
    void buildTax();

    /**
     * 生成运费
     */
    void buildFreight();

    /**
     * 生成折扣
     */
    void buildDiscount();

    /**
     * 获取账单
     * @return  账单
     */
    Object getBill();

    default boolean isTodayTheGivenDay(int theDay, int field) {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(field) == theDay;
    }

}
