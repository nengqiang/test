package com.hnq.practice.builderpattern.practice01;

import com.hnq.practice.builderpattern.practice01.management.BillMgt;
import com.hnq.practice.builderpattern.practice01.management.Order;

/**
 * 场景介绍:
 *  某购物平台需要将订单经过费用计算转换为账单。账单由基本价格、会员折扣、关税、运费等几个部分构成。
 * 对于“本地购”和“海外购”两种不同的商品，各价格构成部分的计算方法并不相同。
 * 现在使用生成器模式将账单计算过程与账单类型分离。
 *
 * @author henengqiang
 * @date 2019/03/08
 */
public class Application {

    public static void main(String[] args) {
        buildTest();
    }

    private static void buildTest() {
        // 订单管理对象
        BillMgt billMgt = new BillMgt();
        // 国内订单
        Order domesticOrder = new Order("婴儿手口巾", 20f, 0, false);
        billMgt.outputBill(domesticOrder);
        System.out.println();
        // 国际订单
        Order internationalOrder = new Order("婴儿车", 3750f, 1, true);
        billMgt.outputBill(internationalOrder);
    }

}
