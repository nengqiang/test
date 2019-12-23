package com.hnq.study.service.impl;

import com.hnq.study.annotation.MyService;
import com.hnq.study.service.HelloService;

/**
 * @author henengqiang
 * @date 2019/12/05
 */
@MyService
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hello, " + name + "!";
    }
}
