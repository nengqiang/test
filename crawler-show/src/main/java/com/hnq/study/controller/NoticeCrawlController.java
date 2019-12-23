package com.hnq.study.controller;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.hnq.study.consts.Constants;
import com.hnq.study.request.CsdnCrawRequest;
import com.hnq.study.request.ZhihuCrawRequest;
import com.hnq.study.service.crawler.NoticeCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author henengqiang
 * @date 2019/06/13
 */
@RestController
@RequestMapping(value = "/crawl/overview")
public class NoticeCrawlController {

    @Autowired
    private NoticeCrawlerService noticeCrawlerService;

    @RequestMapping(value = "/csdn")
    public String crawlCsdnOverview(@RequestBody CsdnCrawRequest csdnCrawRequest) {
        try {
            Preconditions.checkNotNull(csdnCrawRequest.getSearch());
            Map<String, String> extra = Maps.newHashMap();
            extra.put("search", csdnCrawRequest.getSearch());
            Long taskId = noticeCrawlerService.startCrawlerByMQ(Constants.CSDN_CRAWL_WEBSITE_NAME, extra);
            return "Send craw info success! taskId=" + taskId;
        } catch (Exception e) {
            return "failed!";
        }
    }

    @RequestMapping(value = "/zhihu")
    public String crawlZhihuOverview(@RequestBody ZhihuCrawRequest zhihuCrawRequest) {
        try {
            Preconditions.checkNotNull(zhihuCrawRequest.getSearch());
            Map<String, String> extra = Maps.newHashMap();
            extra.put("search", zhihuCrawRequest.getSearch());
            Long taskId = noticeCrawlerService.startCrawlerByMQ(Constants.ZHIHU_CRAW_WEBSITE_NAME, extra);
            return "Send craw info success! taskId=" + taskId;
        } catch (Exception e) {
            return "failed!";
        }
    }

}
