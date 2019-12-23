package com.hnq.practice.prototypepattern.practice01;

import com.google.common.collect.Lists;
import com.hnq.practice.prototypepattern.practice01.derive.PurchaseOrder;
import com.hnq.practice.prototypepattern.practice01.derive.SalesOrder;
import com.hnq.practice.prototypepattern.practice01.father.Order;
import com.hnq.practice.prototypepattern.practice01.management.OrderMgt;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 场景介绍：
 *  某电商平台使用采购单和销售单两种主要单据，除了单据id、单据创建时间、单据创建人等共有信息外，每种单据还包含个性化信息。
 * 平台拥有统一的单据管理功能。利用该功能使用者可以查看自己创建的各单据的明细，并可以以已有单据为基础（原型）创建新的单据。
 *
 * @author henengqiang
 * @date 2019/03/13
 */
public class Application {

    public static void main(String[] args) throws CloneNotSupportedException {
        cloneTest();
    }

    private static void cloneTest() throws CloneNotSupportedException {
        // 原始单据集合
        List<Order> srcOrders = Lists.newArrayList();
        // 原始采购单
        PurchaseOrder srcPurchaseOrder = new PurchaseOrder(
                "c0f55f62-4979-4e87-8cd9-1c556894e2bb",
                new Date(new GregorianCalendar(2017, Calendar.JANUARY,25).getTimeInMillis()),
                "张三",
                "婴儿口水巾",
                400
        );
        // 原始销售单
        SalesOrder srcSalesOrder = new SalesOrder(
                "12fb4958-bee2-4c89-8cf8-edea1177b21f",
                new Date(new GregorianCalendar(2017, Calendar.JANUARY,13).getTimeInMillis()),
                "张三",
                "婴儿车",
                1,
                1280.0D
        );
        srcOrders.add(srcPurchaseOrder);
        srcOrders.add(srcSalesOrder);
        // 单据管理类对象
        OrderMgt orderMgt = new OrderMgt();
        orderMgt.refAdd(srcOrders);
    }

}
