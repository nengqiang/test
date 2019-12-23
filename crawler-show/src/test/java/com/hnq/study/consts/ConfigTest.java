package com.hnq.study.consts;

import com.hnq.study.BaseTest;
import com.hnq.study.redis.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author henengqiang
 * @date 2019/05/07
 */
class ConfigTest extends BaseTest {

    @Autowired
    private Config config;

    @Autowired
    private RedisUtils redisUtils;

    @Test
    void configTest() {
        System.out.println(config);
    }

    @Test
    void redisTest() {
        System.out.println(redisUtils.exists("saas_gateway:routing-server-address:sid:A09914A431BF2D7D4D130513BECE2380"));
    }

}