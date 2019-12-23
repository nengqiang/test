package com.hnq.study.service.crawler;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hnq.study.consts.Config;
import com.hnq.study.mq.MqProducer;
import com.hnq.study.util.GenUtils;
import com.hnq.toolkit.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * 发送MQ消息通知爬虫框架爬取数据
 *
 * @author henengqiang
 * @date 2019/05/05
 */
@Service
@Slf4j
public class NoticeCrawlerService {

    @Autowired
    private Config config;

    @Autowired
    private MqProducer mqProducer;

    /** -------------------- //
     |                        |
     |      By RocketMQ       |
     |                        |
     |-----------------------*/

    public Long startCrawlerByMQ(String websiteName, Map<String, String> extraMap) {
        Long taskId = Long.valueOf(GenUtils.generateTaskId(15));
        Map<String, Object> msgBodyMap = Maps.newHashMap();
        msgBodyMap.put("websiteName", websiteName);
        msgBodyMap.put("taskId", taskId);
        msgBodyMap.put("extra", extraMap);
        log.debug("messageBody={}", msgBodyMap);
        String messageBody = JSONObject.toJSONString(msgBodyMap);
        mqProducer.send(messageBody, config.getTopic(), config.getTag(), config.getKey());
        return taskId;
    }

    /** -------------------- //
     |                        |
     |      By HttpClient     |
     |                        |
     |-----------------------*/

    public String startCrawlerByHttpClient(String websiteName, Map<String, String> extraMap) {
        try {
            String taskId = GenUtils.generateTaskId(15);
            CookieStore cookieStore = new BasicCookieStore();
            CloseableHttpClient httpClient = HttpUtils.getHttpClient(cookieStore);
            HttpPost post = new HttpPost(config.getCrawlerUrl());
            HttpUtils.constructHeader(post, HttpUtils.getSessionId());

            String jsonStr = getCrawlerConfig(taskId, websiteName, JSONObject.toJSON(extraMap));
            StringEntity entity = new StringEntity(jsonStr, Charset.forName("UTF-8"));
            entity.setContentEncoding("UTF-8");
            // 发送Json格式的数据请求
            entity.setContentType("application/json");
            post.setEntity(entity);
            HttpResponse res = httpClient.execute(post);
            log.info("--已调用爬数接口-- 参数=" + jsonStr);
            String responseStr = HttpUtils.getResponseString(res);
            log.info(responseStr);
            return taskId;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getCrawlerConfig(String taskId, String websiteName, Object extra) {
        Map<String, Object> msgBodyMap = Maps.newHashMap();
        msgBodyMap.put("websiteName", websiteName);
        msgBodyMap.put("taskId", taskId);
        msgBodyMap.put("extra", extra);

        Map<String, String> bodyMap = Maps.newHashMap();
        bodyMap.put("topic", "spider_extra");
        bodyMap.put("key", "key");
        bodyMap.put("tag", "login_info");
        bodyMap.put("messageBody", JSONObject.toJSONString(msgBodyMap));
        return JSONObject.toJSONString(bodyMap);
    }

}
