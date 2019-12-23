package com.hnq.practice.strategypattern.practice01;

import com.hnq.practice.strategypattern.practice01.concretestrategy.CommFeeCalcForPkgA;
import com.hnq.practice.strategypattern.practice01.concretestrategy.CommFeeCalcForPkgB;
import com.hnq.practice.strategypattern.practice01.context.CommBill;

/**
 * 1 Strategy
 * Strategy是策略接口，定义了各算法接口方法。
 * 2 ConcreteStrategy
 * ConcreteStrategy是具体策略，实现了接口Strategy。
 * 3 Context
 * Context是上下文。Context在内部维护了策略Strategy的实例，必要时调用Strategy实例提供的算法方法。
 *
 * 场景介绍
 * 某移动通讯公司提供A、B两种话费套餐，其账单系统可以通过用户电话号码输出用户话费账单明细。
 *
 * @author henengqiang
 * @date 2019/07/31
 */
public class Application {

    public static void main(String[] args) {
        CommBill commBill1 = new CommBill("15011559999", new CommFeeCalcForPkgA());
        commBill1.print();
        CommBill commBill2 = new CommBill("15011559998", new CommFeeCalcForPkgB());
        commBill2.print();
    }

}
