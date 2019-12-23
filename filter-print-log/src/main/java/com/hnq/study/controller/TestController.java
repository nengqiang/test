package com.hnq.study.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author henengqiang
 * @date 2018/11/14
 */
@RestController
@SuppressWarnings("unchecked")
public class TestController {

    @RequestMapping(value = "/hello")
    public ResponseEntity sayHello(@RequestParam("name") String name) {
        return new ResponseEntity("Hello, " + name + "!", HttpStatus.OK);
    }

    @RequestMapping(value = "/hi")
    public String sayHi(@RequestParam("name") String name) {
        return "Hi, " + name;
    }

}
