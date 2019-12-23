package com.hnq.study.bluebridgecup;

import com.hnq.toolkit.consts.DateConsts;
import com.hnq.toolkit.util.DateUtils;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * 1949年的国庆节（10月1日）是星期六。
 * 今年（2012）的国庆节是星期一。
 * 那么，从建国到现在，有几次国庆节正好是星期日呢？
 *
 * @author henengqiang
 * @date 2019/06/10
 */
@Slf4j
public class SundayQ {

    public static void main(String[] args) throws ParseException {
        String firstNationDayStr = "1949-10-01";
        Date firstNationDay = DateUtils.parse(firstNationDayStr, DateConsts.PATTERN_DATE_1);
        int dayNums = howManyNationDayIsSunday(firstNationDay);
        System.out.println("howManyNationDayIsSunday: " + dayNums);
    }

    private static int howManyNationDayIsSunday(Date firstNationDay) {
        int count = 0;
        if (isSunday(firstNationDay)) {
            count++;
        }
        int years = Math.abs(DateUtils.year(firstNationDay) - DateUtils.year(new Date()));
        Date date = firstNationDay;
        for (int i = 0; i < years; i++) {
            date = DateUtils.plusYears(date, 1);
            if (isSunday(date)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isSunday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            log.debug(DateUtils.format(date, DateConsts.PATTERN_DATE_1) + "是星期日");
            return true;
        }
        log.debug(DateUtils.format(date, DateConsts.PATTERN_DATE_1) + "是" + switchDayOfWeek(calendar));
        return false;
    }

    private static String switchDayOfWeek(Calendar calendar) {
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1: return "星期日";
            case 2: return "星期一";
            case 3: return "星期二";
            case 4: return "星期三";
            case 5: return "星期四";
            case 6: return "星期五";
            case 7: return "星期六";
            default: return "";
        }
    }

}
