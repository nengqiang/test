package com.hnq.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author henengqiang
 * @date 2019/08/01
 */
@SpringBootApplication
@Slf4j
public class CrawlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrawlerApplication.class);
        log.info("CrawlerApplication Start Success!");
    }

}
