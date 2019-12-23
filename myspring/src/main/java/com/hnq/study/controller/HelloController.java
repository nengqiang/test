package com.hnq.study.controller;

import com.hnq.study.annotation.MyController;
import com.hnq.study.annotation.MyRequestMapping;
import com.hnq.study.annotation.MyRequestParam;
import com.hnq.study.annotation.MyResource;
import com.hnq.study.service.HelloService;

/**
 * @author henengqiang
 * @date 2019/12/05
 */
@MyController
@MyRequestMapping("/hello")
public class HelloController {

    @MyResource
    private HelloService helloService;

    @MyRequestMapping("/say")
    public String hello(@MyRequestParam("name") String name) {
        return helloService.sayHello(name);
    }

}
