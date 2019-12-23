package com.hnq.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author henengqiang
 * @date 2019/05/05
 */
@SpringBootApplication
@Slf4j
@ImportResource(value = {"classpath:spring/appContext.xml"})
public class CrawlerShowApp {

    public static void main(String[] args) {
        SpringApplication.run(CrawlerShowApp.class);
        log.info(">>> CrawlerShowApp Start Success! <<<");
    }

}

