package com.hnq.practice.builderpattern.practice01.management;

import com.hnq.practice.builderpattern.practice01.abstractbuilder.IBillBuilder;
import com.hnq.practice.builderpattern.practice01.concretebuilder.DomesticBillBuilder;
import com.hnq.practice.builderpattern.practice01.concretebuilder.InternationalBillBuilder;
import com.hnq.practice.builderpattern.practice01.director.BillDirector;

/**
 * BillMgt 是账单管理类，用于账单的各种管理操作。对应于生成器模式的参与者，BillMgt 是客户。
 *
 * @author henengqiang
 * @date 2019/03/08
 */
public class BillMgt {

    /**
     * 输出账单信息
     * @param order 订单
     */
    public void outputBill(Order order) {
        // 账单生成器
        IBillBuilder billBuilder;
        // 国内订单
        int domesticBill = 0;
        if (domesticBill == order.getOrderType()) {
            billBuilder = new DomesticBillBuilder();
        } else {
            billBuilder = new InternationalBillBuilder();
        }
        // 账单导向器
        BillDirector billDirector = new BillDirector(billBuilder);
        billDirector.constructBill(order);
        // 订单
        Object bill = billBuilder.getBill();
        System.out.println("商品名：" + order.getProductName());
        System.out.println(bill);
    }

}
