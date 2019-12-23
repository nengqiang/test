package com.hnq.test.common;

import com.alibaba.fastjson.JSONObject;
import com.hnq.toolkit.util.http.HttpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * @author henengqiang
 * @date 2019/06/19
 */
@Slf4j
public class CrawlUtils {

    private static final Random R = new Random();

    public static String getProxyStr() {
        String result = null;
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

    public static Invocable createInvocable(String fileName, String charsetName) throws Exception {
        if (StringUtils.isBlank(charsetName) || StringUtils.isBlank(fileName)) {
            throw new IllegalArgumentException("empty input");
        }
        InputStream inputStream = CrawlUtils.class.getClassLoader().getResourceAsStream(fileName);
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");
        scriptEngine.eval(new InputStreamReader(inputStream, charsetName));
        return (Invocable) scriptEngine;
    }

    public static Long generateTaskId(int num) {
        StringBuilder taskId = new StringBuilder();
        for (int i = 0; i < num; i++) {
            taskId.append(R.nextInt(10));
            // 首位不能为0
            if (i == 0 && taskId.charAt(0) == '0') {
                taskId.replace(0, 1, "1");
            }
        }
        return Long.valueOf(taskId.toString());
    }

    public static Long generateTaskId() {
        return R.nextLong();
    }
}
