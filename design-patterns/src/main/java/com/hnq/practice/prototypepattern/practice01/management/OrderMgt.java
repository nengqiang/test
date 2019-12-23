package com.hnq.practice.prototypepattern.practice01.management;

import com.google.common.collect.Lists;
import com.hnq.practice.prototypepattern.practice01.derive.SalesOrder;
import com.hnq.practice.prototypepattern.practice01.father.Order;

import java.util.List;

/**
 * OrderMgt 是单据管理类，实现了单据的批量参考新增功能。对应于原型模式的参与者，OrderMgt 是 Client。
 *
 * @author henengqiang
 * @date 2019/03/13
 */
public class OrderMgt {

    /**
     * 参考新增
     * @param srcOrders 原始单据集合
     */
    public void refAdd(List<Order> srcOrders) throws CloneNotSupportedException {
        System.out.println("----------------> 原始单据：");
        srcOrders.forEach(System.out::println);
        // 目标单据
        List<Order> desOrders = Lists.newArrayList();
        for (Order srcOrder : srcOrders) {
            Order desOrder = srcOrder.clone();
            if (desOrder instanceof SalesOrder) {
                ((SalesOrder) desOrder).setUnitPrice(getUnitPrice(((SalesOrder) desOrder).getProductName()));
            }
            desOrders.add(desOrder);
        }
        System.out.println("----------------> 新增单据：");
        desOrders.forEach(System.out::println);
    }

    /**
     * 获取产品单价
     * @param productName   产品名称
     * @return              单价
     */
    private Double getUnitPrice(String productName) {
        double unitPrice = 0.0D;
        String product1Name = "婴儿车";
        if (product1Name.equals(productName)) {
            unitPrice = 999.0D;
        }
        return unitPrice;
    }

}
