package com.hnq.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author henengqiang
 * @date 2019/05/16
 */
@SpringBootApplication
@Slf4j
public class MultithreadingApp {

    public static void main(String[] args) {
        SpringApplication.run(MultithreadingApp.class);
        log.info(">>> MultithreadingApp Start Success!");
    }

}
