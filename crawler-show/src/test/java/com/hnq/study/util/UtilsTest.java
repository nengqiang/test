package com.hnq.study.util;

import com.alibaba.fastjson.JSONObject;
import com.hnq.study.BaseTest;
import com.hnq.study.model.zhihu.ZhiHuOverviewBO;
import com.hnq.study.redis.RedisUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author henengqiang
 * @date 2019/05/09
 */
class UtilsTest extends BaseTest {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    void genTaskIdTest() {
        for (int i = 0; i < 20; i++) {
            System.out.println(GenUtils.generateTaskId(15));
        }
    }

    @Test
    void redisListTest() {
        String key = "opinionDetails.10291.326951133963461";
        List<String> valueList = redisUtils.lRangeByJedis(key, 0, -1);
        System.out.println(valueList);
        for (String str : valueList) {
            ZhiHuOverviewBO zhiHuOverviewBO = JSONObject.parseObject(str, ZhiHuOverviewBO.class);
            System.out.println(zhiHuOverviewBO);
        }
    }

    @Test
    void emojiToXinTest() {
        String str = "you're a 🌚, 😂";
        System.out.println(StrUtils.filterEmoji(str));
    }

    @Test
    void download() {
        String key = "task.cookie.353227871781007360";
        List<String> values = redisUtils.lRangeByJedis(key, 0, -1);
        System.out.println(values);
    }

}
