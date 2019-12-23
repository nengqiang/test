package com.hnq.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author henengqiang
 * @date 2018/12/3
 */
@SpringBootApplication
@ImportResource("classpath:spring.xml")
public class JCaptchaCodeRecognizeApplication {

    public static void main(String[] args) {
        SpringApplication.run(JCaptchaCodeRecognizeApplication.class);
        System.out.println("==========>> JCaptchaCodeRecognizeApplication 启动成功！");
    }

}
