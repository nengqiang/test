package com.hnq.study.config;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author henengqiang
 * @date 2019/06/04
 */
@Slf4j
public class Properties {

    /**
     * 把application.properties里的配置信息保存在这个Map里
     */
    private static Map<String, String> properties;

    /**
     * 初始化 然后把properties保存在内存里
     */
    private Properties() {
        properties = Maps.newHashMap();
        java.util.Properties pro = new java.util.Properties();
        String onlyName = "application.properties";
        InputStream in = ClassLoader.getSystemResourceAsStream(onlyName);
        try {
            pro.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Enumeration em = pro.propertyNames();
        while (em.hasMoreElements()) {
            String key = (String) em.nextElement();
            String value = pro.getProperty(key);
            properties.put(key, value);
        }
    }

    public static String getProperty(String key) {
        if (MapUtils.isEmpty(properties)) {
            new Properties();
            log.debug("initial Properties.");
        }
        return properties.get(key);
    }

}
