package com.hnq.toolkit.util.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hnq.toolkit.consts.DateConsts;
import com.hnq.toolkit.util.JsonPathUtils;
import com.hnq.toolkit.util.RegexUtils;
import com.hnq.toolkit.util.StrUtils;
import com.hnq.toolkit.util.http.exception.HttpException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.*;

/**
 * @author henengqiang
 * @date 2019/07/01
 */
@Slf4j
class HttpServiceTest {

    @Test
    void getTest() {
        String url = "https://www.baidu.com";
        Map<String, Object> params = Maps.newHashMap();
        params.put("wd", "http");
        try {
            String response = HttpService.get(url, params);
            Assertions.assertNotNull(response);
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }

    /**
     * 浦发银行改版后的网银账单登录
     */
    private static String seedurl = "https://ebill.spdbccc.com.cn/acsp-web/billMain?cardNbr=DYPLEDv3T$$4$cTLfidP/DZCxPVLEfOO&category=0001&idNo=kAs6jNxMh5LvH8LZ4WBajsBu7hSTneM9&source=DZ";

    private static final String TOKEN_URL = "https://ebill.spdbccc.com.cn/acsp-service/acsp-app-card/web/token/getToken";

    private static String tokenId = StringUtils.EMPTY;

    private static final String NEW_URL_FLAG = "acsp-web/billMain";

    private static final String REQUEST_URL_NEW = "https://ebill.spdbccc.com.cn/acsp-service/acsp-app-card/web/toLogin/validateCode";

    private static final String VALIDATE_URL_NEW = "https://ebill.spdbccc.com.cn/acsp-service/acsp-app-card/web/toLoginMail/sendSmsOfMail";

    private static final String CONFIRM_URL_NEW = "https://ebill.spdbccc.com.cn/acsp-service/acsp-app-card/web/toLoginMail/confirmPcMailLogin";

    private static Scanner sc = new Scanner(System.in);

    static void main(String[] args) {
        new HttpServiceTest().simulate();
    }

    private void simulate() {
        tokenId = getTokenId(seedurl);
        System.err.println(tokenId);

        rePic();
        String picCode = validatePicAndSendSms();
        validateSmsCode(picCode);
    }

    private void rePic() {
        byte[] validCodeBytes = null;
        Map<String, String> params = getParamMap(seedurl);

        if (StringUtils.contains(seedurl, NEW_URL_FLAG)) {
            try {
                /*
                 * 公共参数由 {@link SmsCodeMain#getParamMap(java.lang.String, java.lang.String)} 传递过来，额外参数这里添加
                 */
                log.info("params={}", params);
                MoreHttpRequest request = MoreHttpRequest.post(REQUEST_URL_NEW)
                        .addHeader("Content-Type", "application/json;charset=UTF-8")
                        .setReferer(seedurl)
                        .setEntity(JSON.toJSONString(params));
                validCodeBytes = HttpService.send(request, byte[].class);
            } catch (Exception e) {
                log.error("获取picCode请求失败", e);
            }
            System.err.println(Base64.encodeBase64String(validCodeBytes));
        }
    }

    private String validatePicAndSendSms() {
        System.out.println("输入图片验证码：\n");
        String imageCode = sc.nextLine();
        Map<String, String> params = getParamMap(seedurl);

        String pageContent = StringUtils.EMPTY;
        if (StringUtils.contains(seedurl, NEW_URL_FLAG)) {
            try {
                /*
                 * 公共参数由 {@link SmsCodeMain#getParamMap(java.lang.String, java.lang.String)} 传递过来，额外参数这里添加
                 */
                params.put("code", imageCode);

                MoreHttpRequest request = MoreHttpRequest.post(VALIDATE_URL_NEW)
                        .addHeader("Content-Type", "application/json")
                        .setReferer(seedurl)
                        .setEntity(JSON.toJSONString(params));
                pageContent = HttpService.send(request);
                if (StringUtils.contains(pageContent, "\"code\":\"000000\"")) {
                    log.info("图片验证码校验成功！");
                }
            } catch (Exception e) {
                log.error("校验picCode请求异常, pageContent={}", pageContent, e);
            }
        }
        return imageCode;
    }

    private void validateSmsCode(String imgCode) {
        System.out.println("输入短信验证码：\n");
        String smsCode = sc.nextLine();

        String pageContent = StringUtils.EMPTY;
        if (StringUtils.contains(seedurl, NEW_URL_FLAG)) {
            try {
                Map<String, String> param = getParamMap(seedurl);
                param.put("deviceType", "PC");
                param.put("code", imgCode);
                param.put("smsCode", smsCode);
                Map<String, Object> itParam = Maps.newHashMap(param);
                MoreHttpRequest request = MoreHttpRequest.post(CONFIRM_URL_NEW)
                        .addHeader("Content-Type", "application/json")
                        .setReferer(seedurl)
                        .setEntity(JSON.toJSONString(itParam));
                pageContent = HttpService.send(request);
                if (StringUtils.contains(pageContent, "\"code\":\"000000\"")) {
                    log.debug("spdbccc sms code validate success!");
                    // 把相关参数放入上下文中 配置里的请求需要
                    String timeSq = RegexUtils.group(pageContent, "timeSq\\\\\":\\\\\"([^\\\\]+)", 1);
                    String source = RegexUtils.group(pageContent, "source\\\\\":\\\\\"([^\\\\]+)", 1);
                    log.info("timeSq={}, source={}", timeSq, source);
                }
            } catch (Exception e) {
                log.error("校验smsCode请求异常, pageContent={}", pageContent, e);
            }
        }
    }

    private String getTokenId(String seedurl) {
        String pageContent = StringUtils.EMPTY;
        try {
            Map<String, Object> params = Maps.newHashMap();
            params.put("source", "WX");
            MoreHttpRequest request = MoreHttpRequest.post(TOKEN_URL)
                    .addHeader("Content-Type", "application/json;charset=UTF-8")
                    .setReferer(seedurl)
                    .setEntity(JSON.toJSONString(params));
            pageContent = HttpService.send(request);
            log.info("tokenId pageContent={}", pageContent);
        } catch (Exception e) {
            log.error("获取tokenId请求失败, pageContent={}", pageContent, e);
        }
        return RegexUtils.group(pageContent, "tokenId\\\\\":\\\\\"(\\w+)", 1);
    }

    private Map<String, String> getParamMap(String seedurl) {
        Map<String, String> paramMap = Maps.newHashMap();
        try {
            String accNbr = URLDecoder.decode(RegexUtils.group(seedurl, "cardNbr=([^&]+)&*", 1), StandardCharsets.UTF_8.name());
            String category = URLDecoder.decode(RegexUtils.group(seedurl, "category=([\\d]+)&*", 1), StandardCharsets.UTF_8.name());
            String idNo = URLDecoder.decode(RegexUtils.group(seedurl, "idNo=([^&]+)&*", 1), StandardCharsets.UTF_8.name());
            String source = URLDecoder.decode(RegexUtils.group(seedurl, "source=([\\w]+)&*", 1), StandardCharsets.UTF_8.name());
            paramMap.put("cardNbr", accNbr);
            paramMap.put("category", category);
            paramMap.put("idNo", idNo);
            // 一般来说证件类型是身份证
            paramMap.put("idType", "HSvmIGSbSLA=");
            paramMap.put("source", source);
            paramMap.put("tokenId", tokenId);
        } catch (Exception e) {
            log.error("组装请求参数异常, seedurl={}, param={}", seedurl, paramMap, e);
        }
        return paramMap;
    }

    /**
     * 投诉类舆情监控
     */

    private static Date crawlDate;

    static {
        try {
            crawlDate = DateUtils.parseDate(DateFormatUtils.format(DateUtils.addDays(new Date(), -2), DateConsts.PATTERN_DATE_1), DateConsts.PATTERN_DATE_1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void parallelTest() {
        /*Task t1 = new Task();
        t1.setKeyword("玖富");
        Task t2 = new Task();
        t2.setKeyword("微信");
        Task t3 = new Task();
        t3.setKeyword("宜人贷");
        ThreadPoolUtils.execute(t1);
        ThreadPoolUtils.execute(t2);
        ThreadPoolUtils.execute(t3);*/
        List<Object> resultList = new ArrayList<>();
//        resultList.addAll(parallelCrawl("玖富"));
//        resultList.addAll(parallelCrawl("微信"));
        resultList.addAll(parallelCrawl("功夫贷"));
        resultList.forEach(System.out::println);
    }

    private static class Task implements Runnable {
        @Getter
        @Setter
        private String keyword;
        @Override
        public void run() {
            parallelCrawl(keyword);
        }
    }

    static List<Object> parallelCrawl(String keyword) {
        log.info("聚投诉检索插件启动成功, keyword={}", keyword);
        String url = "https://ts.21cn.com/front/api/search/searchPostList.do?pageNo={}&title={}&listType=1";
        String referer = "https://ts.21cn.com/home/search?keyword={}";
        String pageContent = StringUtils.EMPTY;
        List<Object> resultList = new ArrayList<>();
        try {
            boolean pageTurn = true;
            int page = 0;
            int proxyCount = 0;
            String proxy = getProxyStr("123");
            do {
                page++;
                proxyCount++;
                if (proxyCount >= 5) {
                    proxy = getProxyStr("123");
                }
                String theUrl = StrUtils.format(url, page, URLEncoder.encode(keyword, StandardCharsets.UTF_8.name()));
                String theReferer = StrUtils.format(referer, URLEncoder.encode(keyword, StandardCharsets.UTF_8.name()));
                MoreHttpRequest request = MoreHttpRequest.get(theUrl).setReferer(theReferer).setProxy(proxy);
                pageContent = HttpService.send(request);
                if (StringUtils.contains(pageContent, "登录聚投诉")) {
                    break;
                }
                List<String> data = JsonPathUtils.readAsList(pageContent,"$.postList");
                for (String o : data) {
                    String time = JsonPathUtils.readAsString(o, "$.ctime");
                    Date pDate = new Date(Long.parseLong(time) * 1000);
                    if (pageTurn && pDate.compareTo(crawlDate) < 0) {
                        pageTurn = false;
                    }
                    if (pDate.compareTo(crawlDate) >= 0) {
                        String title = JsonPathUtils.readAsString(o, "$.title");
                        String overview = JsonPathUtils.readAsString(o, "$.shortTopic");
                        String author = JsonPathUtils.readAsString(o, "$.username");
                        String link = "https://ts.21cn.com/tousu/show/id/" + JsonPathUtils.readAsString(o, "$.id");
                        if (StringUtils.contains(title, keyword) || StringUtils.contains(overview, keyword)) {
                            Map<String, Object> resultMap = new HashMap<>(8);
                            resultMap.put("author", author);
                            resultMap.put("title", title);
                            resultMap.put("time", pDate);
                            resultMap.put("link", link);
                            resultMap.put("overview", overview);
                            resultList.add(resultMap);
                        }
                    }
                }
            } while (pageTurn);
        } catch (Exception e) {
            log.error("聚投诉检索抓取失败, keyword={}, response={}", keyword, pageContent, e);
        }
        return resultList;
    }

    private static String getProxyStr(String site) {
        String result = null;
        try {
            String url = "http://wiseproxy.saas.treefinance.com.cn/wiseproxy/service/getProxy?site=" + site;
            String resultStr = HttpService.get(url);
            log.debug("proxyStr=" + resultStr);
            JSONObject obj = JSONObject.parseObject(resultStr);
            result = obj.getString("proxy");
        } catch (Exception e) {
            log.error("error to get proxy.", e);
        }
        return result;
    }

    @Test
    void proxyTest() {
        System.out.println(getProxyStr("jutousu"));
    }


}
