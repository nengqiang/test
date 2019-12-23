package com.hnq.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author henengqiang
 * @date 2018/11/1
 */
@SpringBootApplication
@ImportResource(value = {"classpath:spring/appContext.xml"})
public class LocalCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocalCacheApplication.class);
        System.out.println("=====>> LocalCacheApplication 启动成功！");
    }

}
