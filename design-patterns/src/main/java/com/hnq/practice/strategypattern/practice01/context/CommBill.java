package com.hnq.practice.strategypattern.practice01.context;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hnq.practice.strategypattern.practice01.strategy.ICommFeeCalc;
import com.hnq.toolkit.consts.DateConsts;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * CommBill是通讯账单类，实现了用户账单的打印功能。对应于策略模式的参与者，CommBill是上下文Context。
 *
 * @author henengqiang
 * @date 2019/07/31
 */
public class CommBill {

    private String phoneNum;

    private ICommFeeCalc commFeeCalc;

    public CommBill(String phoneNum, ICommFeeCalc commFeeCalc) {
        this.phoneNum = phoneNum;
        this.commFeeCalc = commFeeCalc;
    }

    public void print() {
        List<Map<String, Object>> records = getRecords();
        System.out.println("用户" + phoneNum + "共有" + records.size() + "条通话记录：");
        for (Map<String, Object> record : records) {
            System.out.println("id：" + record.get("id"));
            System.out.println("呼叫号码：" + record.get("toTel"));
            Date fromDate = (Date) record.get("fromDate");
            Date toDate = (Date) record.get("toDate");
            System.out.println("通话开始时间：" + DateFormatUtils.format(fromDate, DateConsts.MILLS_PATTERN));
            System.out.println("通话结束时间：" + DateFormatUtils.format(toDate, DateConsts.MILLS_PATTERN));
            System.out.println("话费：" + commFeeCalc.calculate(fromDate, toDate) + "元");
            System.out.println("---------------");
        }
    }

    private List<Map<String, Object>> getRecords() {
        Calendar calendar = Calendar.getInstance();
        List<Map<String, Object>> records = Lists.newArrayList();
        String tel = "15011559999";
        if(tel.equals(phoneNum)){
            // 记录1
            Map<String, Object> record1 = Maps.newHashMap();
            record1.put("id", "001");
            record1.put("toTel", "114");
            calendar.set(2017, Calendar.DECEMBER, 5, 8, 50, 20);
            record1.put("fromDate", calendar.getTime());
            calendar.set(2017, Calendar.DECEMBER, 5, 8, 51, 55);
            record1.put("toDate", calendar.getTime());
            records.add(record1);
            // 记录1
            Map<String, Object> record2 = Maps.newHashMap();
            record2.put("id", "002");
            record2.put("toTel", "13436533333");
            calendar.set(2017, Calendar.DECEMBER, 5, 8, 52, 50);
            record2.put("fromDate", calendar.getTime());
            calendar.set(2017, Calendar.DECEMBER, 5, 8, 58, 12);
            record2.put("toDate", calendar.getTime());
            records.add(record2);
        } else {
            // 记录A
            Map<String, Object> record = Maps.newHashMap();
            record.put("id", "001");
            record.put("toTel", "15835456987");
            calendar.set(2017, Calendar.DECEMBER, 3, 18, 10, 32);
            record.put("fromDate", calendar.getTime());
            calendar.set(2017, Calendar.DECEMBER, 3, 18, 11, 42);
            record.put("toDate", calendar.getTime());
            records.add(record);
        }
        return records;
    }
}
