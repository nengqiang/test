package com.hnq.study.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author henengqiang
 * @date 2018/10/25
 */
@RestController
public class TestController {

    @RequestMapping(value = "/index")
    public String index() {
        return "This is index.";
    }

}
