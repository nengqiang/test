package com.hnq.practice.strategypattern.practice01.concretestrategy;

import com.hnq.practice.strategypattern.practice01.strategy.ICommFeeCalc;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * CommFeeCalcForPkgA是套餐A通讯费计算器类，实现了通讯费计算器接口ICommFeeCalc。
 * 对应于策略模式的参与者，CommFeeCalcForPkgA是具体策略ConcreteStrategy。
 *
 * @author henengqiang
 * @date 2019/07/31
 */
public class CommFeeCalcForPkgA implements ICommFeeCalc {

    @Override
    public double calculate(Date fromDate, Date toDate) {
        Instant start = fromDate.toInstant();
        Instant end = toDate.toInstant();
        // 手动除以60是为了不满1分钟按1分钟算，它这个方法貌似是四舍五入
        double talkTime = Math.ceil(ChronoUnit.SECONDS.between(start, end) / 60.0);
        if (talkTime > 3) {
            return 0.5 + (talkTime - 3) * 0.1;
        }
        return 0.5;
    }
}
