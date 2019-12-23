package com.hnq.test.webdetect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.datatrees.spider.share.common.utils.CollectionUtils;
import com.datatrees.spider.share.common.utils.TemplateUtils;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hnq.toolkit.util.JsonPathUtils;
import com.hnq.toolkit.util.SortUtils;
import com.hnq.toolkit.util.UnicodeUtils;
import com.hnq.toolkit.util.xpath.XpathUtils;
import com.treefinance.crawler.framework.util.json.JsonPathUtil;
import com.treefinance.toolkit.util.RegExp;
import com.treefinance.toolkit.util.Strings;
import com.treefinance.toolkit.util.http.HttpService;
import com.treefinance.toolkit.util.http.MoreHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author henengqiang
 * @date 2019/11/11
 */
@Slf4j
class CrawleComplaint {

    @Test
    @SuppressWarnings("unchecked")
    void crawlDetailFromPageListJutousu() {
        String cookie = "Secure; apm_ct=20191021100228449; apm_sid=CFEEBA95EB59185AC14FDC5873BE57B2; apm_uid=0CCD928EC24CCEA1834653DCAD2AF684; apm_ip=122.224.99.210; apm_ua=3162F634BEBE163CDCFF2AF4860979C2; JSESSIONID=aaaO7KJBxfJ-e_aWZOv3w; isFirstLogin=false; userId=150594323; SSON=a63b597219723be3fb1a2d65b7354881aed0498a3d2d60dafa69462e5c1842e31ab78e1e0b62974affdfabdcc8f3b2900be600c6b6e95266b6ec7567f7362e1816d5007ee2d084d280a682a0a9f4e8a88f9bb7a89d1e7ddf257e567a98975752024df2ff4c76f60760bf9c99c5921421f4aa0f1f1dd42a9ebfa0b6e0bd353da63fa3e5ed5b483200d203b73917efb604833feb6242ef15706853f0b965c3a674d51d27f5e8326fef6fbdf5026560714a1bd474d1acf66079e4f5eadc5b7c18306f058a17cee13687; SSONKEY=85c93c81f907b6ffac22cbf4a5c27aba04df3dec23ad7150ac3828a46a7529a5de962a8b12c93c4e8eab0b31403e4464e1c2c1ec5bb3493137bb187ae37af2ae74a1038d3387127a859bf269740ab6ec1fd84f5f4f20cf888a00237ed3fb6bc0212b798607aeb536867a556e88b2ddc996a222fc489f37392dc7117e5a63370c69c68a418a1ca8d70f578b36980e084f7e0d32ffc682dd60; SSONLOGINFO=c2bf9803e5c968273519c296cb28077ea87ac207d9922b4dcc2f075d9de1e82cca4f2cd8972bf7150f756d64c75ecc7b; jutous_session_id=d5d27eecd49193206584e0c3667a9498327c6658622bc5be; isBindMobile=0; channelId=null";
        String keyword = "钱站";
        String merchantLink = "https://ts.21cn.com/merchant/show/id/2352";
        String pageContent = StringUtils.EMPTY;
//        List<Map<String, Object>> listData = crawlListFromMerchantJutousu(merchantLink, keyword, cookie);
        List<Map> listData = readJutousuData();
        try {
            int proxyCount = 0;
            String proxy = getProxyStr("jutousu");
            for (Map map : listData) {
                if (!map.get("overview").toString().contains("...")) {
                    continue;
                }
                proxyCount++;
                if (proxyCount % 2 == 0) {
                    proxy = getProxyStr("jutousu");
                }
                String pageLink = map.get("link").toString();
                pageContent = HttpService.send(MoreHttpRequest.get(pageLink));
                if (StringUtils.isBlank(pageContent)) {
                    continue;
                }
                String postKey = XpathUtils.getValueByXpath("//input[@id='postKeyValue']/@value", pageContent);
                String detailUrl = "https://ts.21cn.com/front/api/post/getPostContent.do?postKey=" + postKey;
                pageContent = HttpService.send(MoreHttpRequest.get(detailUrl).setReferer(merchantLink).setCookies(cookie).setProxy(proxy));
                if (StringUtils.isBlank(pageContent)) {
                    continue;
                }
                String overview = JsonPathUtil.readAsString(pageContent, "$.post.topic")
                        .replaceAll("\\s", "").replaceAll("&*nbsp;", "")
                        .replaceAll("&*amp;", "").replaceAll("<br>", "")
                        .replaceAll("&*quot;", "");
                String author = JsonPathUtil.readAsString(pageContent, "$.post.username");
                map.put("overview", overview);
                map.put("author", author);
                System.err.println("-----------> 第【" + proxyCount + "】个投诉");
                TimeUnit.MILLISECONDS.sleep(2000);
            }
        } catch (Exception e) {
            log.error("聚投诉商家抓取[{}]异常, pageContent={}", keyword, pageContent, e);
        } finally {
            try {
                FileUtils.writeStringToFile(new File("src/main/resources/tousu2/qz6.json"), JSON.toJSONString(listData), Charsets.UTF_8);
            } catch (IOException e) {
                log.error("keyword={},listData写入异常", keyword, e);
            }
        }
    }

    private List<Map<String, Object>> crawlListFromMerchantJutousu(String merchantLink, String keyword, String cookie) {
        String url = "https://ts.21cn.com/json/merchantPostList/merchantId/{}/listType/1/pageNo/{}/offset/{}";
        List<Map<String, Object>> resultList = Lists.newArrayList();
        String pageContent = StringUtils.EMPTY;
        try {
            String merchantId = RegExp.group(merchantLink, "id\\/(\\d+)", 1);
            int page = 0;
            String offset = StringUtils.EMPTY;
            int proxyCount = 0;
            String proxy = getProxyStr("jutousu");
            do {
                page++;
                proxyCount++;
                if (proxyCount % 4 == 0) {
                    proxy = getProxyStr("jutousu");
                }
                String theUrl = TemplateUtils.format(url, merchantId, page, offset);
                pageContent = HttpService.send(MoreHttpRequest.get(theUrl).setProxy(proxy).setReferer(merchantLink).setCookies(cookie));
                if (StringUtils.isEmpty(pageContent) || StringUtils.contains(pageContent, "登录聚投诉")) {
                    break;
                }
                offset = JsonPathUtils.readAsString(pageContent, "$.offset");
                List<String> data = JsonPathUtils.readAsList(pageContent, "$.postList");
                if (CollectionUtils.isEmpty(data)) {
                    break;
                }
                for (String o : data) {
                    String time = JsonPathUtils.readAsString(o, "$.ctime");
                    Date pDate = new Date(Long.parseLong(time) * 1000);
                    String title = JsonPathUtils.readAsString(o, "$.title");
                    String overview = JsonPathUtils.readAsString(o, "$.shortTopic");
                    String shuqiu = JsonPathUtils.readAsString(o, "$.shuqiu");
                    String link = "https://ts.21cn.com/tousu/show/id/" + JsonPathUtils.readAsString(o, "$.id");
                    Map<String, Object> resultMap = new HashMap<>(9);
                    resultMap.put("title", title);
                    resultMap.put("overview", overview);
                    resultMap.put("appeal", shuqiu);
                    resultMap.put("time", pDate);
                    resultMap.put("link", link);
                    resultList.add(resultMap);
                }
                System.err.println("-----------> 第【" + page + "】页");
                if (resultList.size() >= 1000) {
                    break;
                }
            } while (true);
        } catch (Exception e) {
            log.error("聚投商家检索抓取异常, keyword={}, pageContent={}", keyword, pageContent, e);
        } finally {
            try {
                FileUtils.writeStringToFile(new File("src/main/resources/tousu2/qz3.json"), JSON.toJSONString(resultList), Charsets.UTF_8);
            } catch (IOException e) {
                log.error("keyword={},listData写入异常", keyword, e);
            }
        }
        return resultList;
    }

    private List<Map> readJutousuData() {
        String data = null;
        try {
            data = FileUtils.readFileToString(new File("src/main/resources/tousu2/qz5.json"), Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.parseArray(data, Map.class);
    }

    @Test
    @SuppressWarnings("unchecked")
    void crawlDetailFromPageListBlackcat() {
        String merchantLink = "https://tousu.sina.com.cn/company/view/?couid=5866754241";
        String keyword = "功夫贷";
        String pageContent = Strings.EMPTY;
        List<Map<String, Object>> listData = crawListFromMerchantBlackcat(merchantLink, keyword);
        try {
            for (Map map : listData) {
                String pageLink = map.get("link").toString();
                pageContent = HttpService.send(MoreHttpRequest.get(pageLink).setReferer(merchantLink));
                String overview = XpathUtils.getValueByXpath("//div[@class='ts-reply']/[last()]/p[2]/text()", pageContent).replaceAll("\\s", "");
                map.put("overview", overview);
            }
        } catch (Exception e) {
            log.error("黑猫投诉商家抓取[{}]异常, pageContent={}", keyword, pageContent, e);
        } finally {
            try {
                FileUtils.writeStringToFile(new File("src/main/resources/tousu2/gfd_black1.json"), JSON.toJSONString(listData), Charsets.UTF_8);
            } catch (IOException e) {
                log.error("keyword={},listData写入异常", keyword, e);
            }
        }

    }

    private List<Map<String, Object>> crawListFromMerchantBlackcat(String merchantLink, String keyword) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        String url = "https://tousu.sina.com.cn/api/company/received_complaints?callback=jQuery{}&couid={}&type=1&page_size=10&page={}";
        String pageContent = StringUtils.EMPTY;
        try {
            String couid = RegExp.group(merchantLink, "couid=(\\d+)", 1);
            int page = 0;
            int proxyCount = 0;
            String proxy = getProxyStr("heimao");
            do {
                page++;
                proxyCount++;
                if (proxyCount >= 2) {
                    proxyCount = 0;
                    proxy = getProxyStr("heimao");
                }
                String theUrl = TemplateUtils.format(url, genJqueryParam(), couid, page);
                pageContent = HttpService.send(MoreHttpRequest.get(theUrl).setProxy(proxy).setReferer(merchantLink));
                if (StringUtils.isBlank(pageContent)) {
                    break;
                }
                String unicodeData = RegExp.group(pageContent, "\\{jQuery\\w+\\((\\{.*})\\);}", 1);
                if (StringUtils.isBlank(unicodeData)) {
                    break;
                }
                String dataStr = UnicodeUtils.unicodeStrToString(unicodeData);
                if (StringUtils.isEmpty(dataStr)) {
                    break;
                }
                // 去除html标签
                String data = dataStr.replaceAll("</?[^>]+>", "");
                List<String> detail = JsonPathUtil.readAsList(data, "$.result.data.complaints");
                if (CollectionUtils.isEmpty(detail)) {
                    break;
                }
                for (String o : detail) {
                    String time = JsonPathUtil.readAsString(o, "$.main.timestamp");
                    Date pDate = new Date(Long.parseLong(time) * 1000);
                    String title = JsonPathUtil.readAsString(o, "$.main.title");
                    String overview = JsonPathUtil.readAsString(o, "$.main.summary");
                    String appeal = JsonPathUtil.readAsString(o, "$.main.appeal");
                    String author = JsonPathUtil.readAsString(o, "$.author.title");
                    String link = "https:" + JsonPathUtil.readAsString(o, "$.main.url");
                    Map<String, Object> resultMap = new HashMap<>(8);
                    resultMap.put("author", author);
                    resultMap.put("title", title);
                    resultMap.put("time", pDate);
                    resultMap.put("link", link);
                    resultMap.put("overview", overview);
                    resultMap.put("appeal", appeal);
                    resultList.add(resultMap);
                }
                System.err.println("-----------> 第【" + page + "】页");
            } while (true);
        } catch (Exception e) {
            log.error("黑猫投诉商家检索抓取异常 keyword={}, pageContent={}", keyword, pageContent, e);
        }
        return resultList;
    }

    private String genJqueryParam() {
        String a = String.valueOf(Math.random());
        String b = String.valueOf(Math.random());
        return a.substring(2, 15) + b.substring(2, 10) + "_" + a.substring(2, 15);
    }

    private static String getProxyStr(String site) {
        String result = null;
        try {
            String url = "http://wiseproxy.saas.treefinance.com.cn/wiseproxy/service/getProxy?site=" + site;
            String resultStr = com.hnq.toolkit.util.http.HttpService.get(url);
            log.debug("proxyStr=" + resultStr);
            JSONObject obj = JSONObject.parseObject(resultStr);
            result = obj.getString("proxy");
        } catch (Exception e) {
            log.error("error to get proxy.", e);
        }
        return result;
    }

    /*---------------------------------------------------------------------------------------------------------------------
     *
     *
     *
     *
     *
     *
     *
     * bellow is the code to deal data
     *
     *
     *
     *
     *
     *
     *
     * ---------------------------------------------------------------------------------------------------------------------
     */

    @Test
    void transferTousuData() throws IOException {
        String jutousu = FileUtils.readFileToString(new File("src/main/resources/tousu/jutousuall.json"), Charsets.UTF_8);
        String blackcat = FileUtils.readFileToString(new File("src/main/resources/tousu/blackcatall.json"), Charsets.UTF_8);
        StringBuilder res = new StringBuilder();
        JSONArray arr = JSON.parseArray(jutousu);
        arr.addAll(JSON.parseArray(blackcat));
        for (Object a : arr) {
            JSONObject o = (JSONObject) a;
            res.append(o.getString("title")).append("\n");
            if (StringUtils.isNotBlank(o.getString("appeal"))) {
                res.append(o.getString("appeal")).append("\n");
            }
            res.append(o.getString("overview")).append("\n");
            res.append("==============");
        }
        FileUtils.writeStringToFile(new File("src/main/resources/tousu/tousuall.txt"), res.toString(), Charsets.UTF_8);
    }

    @Test
    void dealAppeal() throws IOException {
        String jutousuSource = FileUtils.readFileToString(new File("/Users/hanif/studyProjects/test/crawler/src/main/resources/jutousu.json"), Charset.defaultCharset());
        String blackcatSource = FileUtils.readFileToString(new File("/Users/hanif/studyProjects/test/crawler/src/main/resources/blackcatShuqiu.txt"), Charset.defaultCharset());
        List<String> appeals = Lists.newArrayList();
        appeals.addAll(Arrays.asList(jutousuSource.split(",")));
        appeals.addAll(Arrays.asList(blackcatSource.split(",")));
        Map<String, Integer> appealTimesMap = Maps.newHashMap();
        for (String appeal : appeals) {
            appealTimesMap.merge(appeal, 1, Integer::sum);
        }

        Map<String, Integer> resMap = SortUtils.sortMapByValue(appealTimesMap, false);
        System.out.println(JSON.toJSONString(resMap));
    }

    @Test
    void parseTestData() throws IOException {
        String data = FileUtils.readFileToString(new File("src/main/resources/tousu/tousu测试集.txt"), Charsets.UTF_8);
        List<String> res = Arrays.stream(data.split("\\s\\n")).collect(Collectors.toList())
                .stream().map(s -> s.replaceAll("\\n", " ")).collect(Collectors.toList());
        System.out.println(res);
    }

    @Test
    void test() {
        try {
            FileUtils.writeStringToFile(new File("src/main/resources/tousu2/gfd1.json"), "testtestteststtestsefsesfsefsefsfsefsefes", Charsets.UTF_8);
        } catch (IOException e) {
            log.error("写入异常", e);
        }
    }
}
