package com.hnq.test.toutiao.util;

import com.alibaba.fastjson.JSONObject;
import com.hnq.toolkit.util.http.HttpService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author henengqiang
 * @date 2019/08/02
 */
@Slf4j
public class CrawlUtils {

    private CrawlUtils() {}

    public static String getProxyStr() {
        String result = "";
        try {
            String url = "http://wiseproxy.saas.treefinance.com.cn/wiseproxy/service/getProxy?site=123";
            String resultStr = HttpService.get(url);
            log.debug("proxyStr=" + resultStr);
            JSONObject obj = JSONObject.parseObject(resultStr);
            result = obj.getString("proxy");
        } catch (Exception e) {
            log.error("error to get proxy.", e);
        }
        return result;
    }

}
