package com.hnq.test.toutiao.common;
import	java.util.Random;


import com.google.common.collect.Lists;
import com.hnq.toolkit.util.StrUtils;

import java.util.List;
import java.util.UUID;

/**
 * @author henengqiang
 * @date 2019/08/01
 */
public class Constants {

    private Constants() {}

    public static final String PHONE_UA_MI = "com.ss.android.article.news/734 (Linux; U; Android 9; zh_CN; MI 9 SE; Build/PKQ1.181121.001; Cronet/58.0.2991.0)";

    public static final String PHONR_UA_WEI = "Mozilla/5.0 (Linux; U; Android 8.1.0; zh-cn; BLA-AL00 Build/HUAWEIBLA-AL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/8.9 Mobile Safari/537.36";

    public static final String PHONE_UA_OPPO = "Mozilla/5.0 (Linux; Android 6.0.1; OPPO A57 Build/MMB29M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/63.0.3239.83 Mobile Safari/537.36 T7/10.13 baiduboxapp/10.13.0.10 (Baidu; P1 6.0.1)";

    public static final String PHONE_UA_VIVO = "Mozilla/5.0 (Linux; Android 5.1.1; vivo X6S A Build/LMY47V; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044207 Mobile Safari/537.36 MicroMessenger/6.7.3.1340(0x26070332) NetType/4G Language/zh_CN Process/tools";

    public static final String FANS_URL = "https://is-hl.snssdk.com/user/relation/fans/v2/?user_id={}&offset={}&count={}&device_id={}&channel={}&app_name=news_article&version_code={}&device_platform=android&device_type={}&os_version={}";

    public static final String USER_INFO_URL = "http://is-hl.snssdk.com/user/profile/homepage/v7/?user_id={}&refer=&lid=&device_id={}&ac=wifi&channel={}&aid={}&app_name=news_article&version_code={}&version_name=7.3.5&device_platform=android";

    public static Random random = new Random();

    public static final List<String> CHANNELS = Lists.newArrayList("xiaomi", "ios", "huawei", "oppo", "vivo");

    public static final List<String> DEVICE_TYPES = Lists.newArrayList("MI+9+SE", "iPhone SE", "MI", "MI+9", "oppo", "Huawei");

    public static String randomUserUrl() {
        String deviceId = String.valueOf(Math.random()).replaceAll("\\.", "").substring(1, 12);
        String channel = CHANNELS.get(random.nextInt(CHANNELS.size()));
        String versionCode = deviceId.substring(1, 4);
        String aid = deviceId.substring(0, 2);
        return StrUtils.format(USER_INFO_URL, "{}", deviceId, channel, aid, versionCode);
    }

    public static String randomFansUrl() {
        String deviceId = String.valueOf(Math.random()).replaceAll("\\.", "").substring(1, 11);
        String versionCode = deviceId.substring(1, 4);
        String channel = CHANNELS.get(random.nextInt(CHANNELS.size()));
        String deviceType = DEVICE_TYPES.get(random.nextInt(DEVICE_TYPES.size()));
        return StrUtils.format(FANS_URL, "{}", "{}", "{}", deviceId, channel, versionCode, deviceType, random.nextInt(10));
    }

    public static String randomUerAgent() {
        List<String> agents = Lists.newArrayList(PHONE_UA_MI, PHONR_UA_WEI, PHONE_UA_OPPO, PHONE_UA_VIVO);
        return agents.get(random.nextInt(agents.size())) + UUID.randomUUID().toString();
    }

}
