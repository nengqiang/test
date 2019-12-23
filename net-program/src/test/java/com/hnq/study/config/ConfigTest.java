package com.hnq.study.config;

import com.hnq.study.BaseTest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author henengqiang
 * @date 2019/06/04
 */
class ConfigTest extends BaseTest {

    @Autowired
    private Config config;

    @Test
    void configValueTest() {
        Assert.assertEquals("localhost", config.getServerAddress());
        Assert.assertEquals("6666", config.getPort().toString());
    }

}