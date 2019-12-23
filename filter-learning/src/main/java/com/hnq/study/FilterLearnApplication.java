package com.hnq.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 自定义Filter需要两个步骤：
 * 1. 实现 {@link javax.servlet.Filter} 接口，实现 Filter 方法
 * 2. 添加 @Configuration 注解，将自定义 Filter 加入过滤链
 * @author henengqiang
 * @date 2018/10/25
 */
@SpringBootApplication
public class FilterLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilterLearnApplication.class);
        System.out.println("==========>> FilterLearnApplication 启动成功！");
    }

}
