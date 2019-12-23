package com.hnq.toolkit.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * redis 端口号等配置在{@link spring/appContext-redis.xml}配置文件里
 *
 * @author henengqiang
 * @date 2019/06/27
 */
@Slf4j
@Component
public class RedisUtils {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public <T> T getDataFromRedis(String key, Class<T> clazz) {
        if (StringUtils.isBlank(key)) {
            log.debug("get val from redis but key is blank");
            return null;
        }
        String jsonStr = redisTemplate.opsForValue().get(key);
        if (StringUtils.isBlank(jsonStr)) {
            log.debug("redis获取信息为空 key【{}】", key);
            return null;
        }
        log.info("get data from redis, key: {}  value: {}", key, jsonStr);
        try {
            return JSON.parseObject(jsonStr, clazz);
        } catch (Exception e) {
            log.error("json解析异常", e);
            return null;
        }
    }

    public <T> List<T> listDataFromRedis(String key, Class<T> clazz) {
        if (StringUtils.isBlank(key)) {
            log.debug("get list from redis but key is blank");
            return Collections.emptyList();
        }
        Long size = redisTemplate.opsForList().size(key);
        if (size == null || size <= 0) {
            return Collections.emptyList();
        }
        List<String> range = redisTemplate.opsForList().range(key, 0, size);
        if (CollectionUtils.isEmpty(range)) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>();
        for (String s : range) {
            if (StringUtils.isBlank(s)) {
                continue;
            }
            list.add(JSON.parseObject(s, clazz));
        }
        return list;
    }

}
