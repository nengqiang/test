package com.hnq.study.crawler;

import com.google.common.collect.Maps;
import com.hnq.study.BaseTest;
import com.hnq.study.service.crawler.NoticeCrawlerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author henengqiang
 * @date 2019/05/06
 */
class NoticeCrawlerServiceTest extends BaseTest {

    @Autowired
    private NoticeCrawlerService noticeCrawlerService;

    private String zhiHuWebsiteName = "www.zhihu.com";

    private String csdnWebsiteName = "blog.csdn.net";

    private String yahooWebsiteName = "shopping.yahoo.co";

    @Test
    void requestCrawlerTest() {
        String websiteName = csdnWebsiteName;
        Map<String, String> extra = Maps.newHashMap();
        extra.put("search", "银杏树");
//        String taskId = noticeCrawlerService.startCrawlerByHttpClient(websiteName, extra);
        Long taskId = noticeCrawlerService.startCrawlerByMQ(websiteName, extra);
        System.err.println("taskId=" + taskId);
    }

}