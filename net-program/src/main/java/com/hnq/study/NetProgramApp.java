package com.hnq.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author henengqiang
 * @date 2019/06/04
 */
@SpringBootApplication
@Slf4j
public class NetProgramApp {

    public static void main(String[] args) {
        SpringApplication.run(NetProgramApp.class);
        log.info(">>>> NetProgramApp Start Success! >>>>");
    }

}
