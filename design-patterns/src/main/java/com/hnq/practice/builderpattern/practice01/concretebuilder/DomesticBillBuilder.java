package com.hnq.practice.builderpattern.practice01.concretebuilder;

import com.hnq.practice.builderpattern.practice01.abstractbuilder.IBillBuilder;
import com.hnq.practice.builderpattern.practice01.concreteproduct.DomesticBill;

/**
 * DomesticBillBuilder 是国内账单生成器类，实现了 IBillBuilder 接口，生成的产品是 DomesticBill 类对象。
 * 对应于生成器模式的参与者，DomesticBillBuilder 是具体生成器。
 *
 * 这个生成器的结构使其具有“一次性”的特点。即一个生成器对象只能生成一个产品。
 * 如果想让生成器对象可以复用，需要添加清除生成产品 domesticBill 的方法。
 *
 * @author henengqiang
 * @date 2019/03/08
 */
public class DomesticBillBuilder implements IBillBuilder {

    /**
     * 国内账单
     */
    private DomesticBill domesticBill = new DomesticBill();

    @Override
    public void buildPrice(float basicPrice) {
        domesticBill.setBasicPrice(basicPrice);
        domesticBill.setTotal(domesticBill.getTotal() + basicPrice);
    }

    @Override
    public void buildTax() {

    }

    @Override
    public void buildFreight() {
        // 50以上包邮
        float shippingPrice = 50;
        if (domesticBill.getTotal() > shippingPrice) {
            domesticBill.setFreight(0);
        } else {
            domesticBill.setFreight(10);
        }
        domesticBill.setTotal(domesticBill.getTotal() + domesticBill.getFreight());
    }

    @Override
    public void buildDiscount() {
        // 满100减20
        float discountPrice = 100;
        if (domesticBill.getBasicPrice() > discountPrice) {
            domesticBill.setDiscount(20);
        } else {
            domesticBill.setDiscount(0);
        }
        domesticBill.setTotal(domesticBill.getTotal() - domesticBill.getDiscount());
    }

    @Override
    public Object getBill() {
        return domesticBill;
    }
}
