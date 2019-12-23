package com.hnq.practice.strategypattern.practice01.concretestrategy;

import com.hnq.practice.strategypattern.practice01.strategy.ICommFeeCalc;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * CommFeeCalcForPkgB是套餐B通讯费计算器类，实现了通讯费计算器接口ICommFeeCalc。
 * 对应于策略模式的参与者，CommFeeCalcForPkgB是具体策略ConcreteStrategy。
 *
 * @author henengqiang
 * @date 2019/07/31
 */
public class CommFeeCalcForPkgB implements ICommFeeCalc {

    @Override
    public double calculate(Date fromDate, Date toDate) {
        Instant start = fromDate.toInstant();
        Instant end = toDate.toInstant();
        double talkTime = Math.ceil(ChronoUnit.SECONDS.between(start, end) / 60.0);
        return talkTime * 0.15;
    }
}
