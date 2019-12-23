package com.hnq.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author henengqiang
 * @date 2018/11/28
 */
@SpringBootApplication
public class KaptchaCodeRecognizeApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaptchaCodeRecognizeApplication.class);
        System.out.println("==========>> KaptchaCodeRecognizeApplication 启动成功！");
    }

}
