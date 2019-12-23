package com.hnq.toolkit.util;

import com.hnq.toolkit.consts.BannerConsts;
import com.hnq.toolkit.consts.DateConsts;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

/**
 * @author henengqiang
 * @date 2019/06/06
 */
class ConstsTest {

    @Test
    void timePatternTest() {
        String dateStr = "2019-06-06 17:32:23";
        Date date = null;
        try {
            date = DateUtils.parseDate(dateStr, DateConsts.DEFAULT_PATTERN);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String time1 = DateFormatUtils.format(date, DateConsts.DEFAULT_PATTERN);
        String time2 = DateFormatUtils.format(date, DateConsts.PATTERN_TIME_2);
        String time3 = DateFormatUtils.format(date, DateConsts.PATTERN_TIME_3);
        String date1 = DateFormatUtils.format(date, DateConsts.PATTERN_DATE_1);
        String date2 = DateFormatUtils.format(date, DateConsts.PATTERN_DATE_2);
        String date3 = DateFormatUtils.format(date, DateConsts.PATTERN_DATE_3);
        String date4 = DateFormatUtils.format(date, DateConsts.PATTERN_DATE_4);
        String month1 = DateFormatUtils.format(date, DateConsts.PATTERN_MONTH_1);
        String month2 = DateFormatUtils.format(date, DateConsts.PATTERN_MONTH_2);
        String month3 = DateFormatUtils.format(date, DateConsts.PATTERN_MONTH_3);
        String month4 = DateFormatUtils.format(date, DateConsts.PATTERN_MONTH_4);
        String year = DateFormatUtils.format(date, DateConsts.PATTERN_YEAR);
        Assertions.assertEquals("2019-06-06 17:32:23", time1);
        Assertions.assertEquals("2019年06月06日 17:32:23", time2);
        Assertions.assertEquals("2019/06/06 17:32:23", time3);
        Assertions.assertEquals("2019-06-06", date1);
        Assertions.assertEquals("2019年06月06日", date2);
        Assertions.assertEquals("20190606", date3);
        Assertions.assertEquals("2019/06/06", date4);
        Assertions.assertEquals("2019-06", month1);
        Assertions.assertEquals("2019年06月", month2);
        Assertions.assertEquals("201906", month3);
        Assertions.assertEquals("2019/06", month4);
        Assertions.assertEquals("2019", year);
    }

    @Test
    void genBannerOnlineTest() {
        String result = BannerConsts.genBannerOnline(BannerConsts.FONT_ALLIGATOR, "study hard");
        System.out.println(result);
    }

}
