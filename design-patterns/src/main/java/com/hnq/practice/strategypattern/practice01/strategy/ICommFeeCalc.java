package com.hnq.practice.strategypattern.practice01.strategy;

import java.util.Date;

/**
 * ICommFeeCalc是通讯费计算器接口，定义通讯费计算的相关接口方法。对应于策略模式的参与者，ICommFeeCalc是策略接口Strategy。
 *
 * @author henengqiang
 * @date 2019/07/31
 */
public interface ICommFeeCalc {

    /**
     * 计算话费
     * @param fromDate 起始时间
     * @param toDate   截止时间
     * @return         话费
     */
    double calculate(Date fromDate, Date toDate);

}
