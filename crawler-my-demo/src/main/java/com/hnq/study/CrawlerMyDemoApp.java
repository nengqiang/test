package com.hnq.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author henengqiang
 * @date 2019/05/28
 */
@SpringBootApplication
@Slf4j
public class CrawlerMyDemoApp {

    public static void main(String[] args) {
        SpringApplication.run(CrawlerMyDemoApp.class);
        log.info(">>>>> CrawlerMyDemoApp Start Success! >>>>>");
    }

}
