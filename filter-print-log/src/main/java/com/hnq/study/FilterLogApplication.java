package com.hnq.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author henengqiang
 * @date 2018/11/14
 */
@SpringBootApplication
@Slf4j
public class FilterLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilterLogApplication.class);
        log.info("==========>> FilterLogApplication启动成功！");
    }

}
