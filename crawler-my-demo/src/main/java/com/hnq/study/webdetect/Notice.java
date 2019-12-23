package com.hnq.study.webdetect;

import com.google.common.base.Charsets;
import com.hnq.toolkit.util.StrUtils;
import com.hnq.toolkit.util.http.HttpService;
import com.hnq.toolkit.util.http.MoreHttpRequest;
import com.hnq.toolkit.util.http.exception.HttpException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;

/**
 * @author henengqiang
 * @date 2019/10/30
 */
@Component
@EnableScheduling
@Slf4j
public class Notice {

    @Scheduled(cron = "0 20 0/2 * * ?")
    public void createTask() {
        String url = "http://localhost:8102/webdetect/createTaskSpecial";
        try {
            HttpService.send(MoreHttpRequest.get(url));
            log.info("===> createTask");
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 21 0/2 * * ?")
    public void askSaas() {
        String url = "http://localhost:8102/webdetect/askSaas";
        try {
            HttpService.send(MoreHttpRequest.get(url).setConnectionRequestTimeout(120000));
            log.info("===> askSaas");
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 35 0/2 * * ?")
    public void syncData() {
        String url = "http://localhost:8102/webdetect/syncData?limit=100";
        try {
            HttpService.send(MoreHttpRequest.get(url).setConnectionRequestTimeout(120000));
            log.info("===> syncData");
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 30 0/2 * * ?")
    public void noticeByEmail() {
        String url = "http://localhost:8102/webdetect/notify?keywords={}";
        String keywords = "钱站,玖富,捷信金融,你我贷,中华保险,拍拍贷,马上消费金融,招联金融,Wecash闪银,维信金科,玖富万卡,闪银";
        try {
            HttpService.send(MoreHttpRequest.get(StrUtils.format(url, URLEncoder.encode(keywords, Charsets.UTF_8.name()))).setConnectionRequestTimeout(120000));
            log.info("===> noticeByEmail");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Notice().syncData();
    }

}
