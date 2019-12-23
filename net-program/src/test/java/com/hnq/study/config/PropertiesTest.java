package com.hnq.study.config;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author henengqiang
 * @date 2019/06/04
 */
public class PropertiesTest {

    @Test
    public void getPropertyTest() {
        Assert.assertEquals("6666", Properties.getProperty("net.port"));
        Assert.assertEquals("6666", Properties.getProperty("net.port"));
        Assert.assertEquals("localhost", Properties.getProperty("net.host"));
        System.out.println(Properties.getProperty("net.port"));
        System.out.println(Properties.getProperty("net.port"));
        System.out.println(Properties.getProperty("net.port"));
        System.out.println(Properties.getProperty("net.port"));
    }

}