package com.hnq.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 实现自定义拦截器只需要3步：
 * 1、创建我们自己的拦截器类并实现 {@link org.springframework.web.servlet.HandlerInterceptor} 接口。
 * 2、创建一个Java类继承 {@link org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter}，
 * 并重写 addInterceptors 方法。
 * 2、实例化我们自定义的拦截器，然后将对像手动添加到拦截器链中（在addInterceptors方法中添加）。
 * @author henengqiang
 * @date 2018/10/25
 */
@SpringBootApplication
public class HandlerLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(HandlerLearnApplication.class);
        System.out.println("==========>> HandlerLearnApplication 启动成功！");
    }

}
