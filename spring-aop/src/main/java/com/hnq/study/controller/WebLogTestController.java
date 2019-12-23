package com.hnq.study.controller;

import com.hnq.study.anno.ControllerWebLog;
import com.hnq.study.anno.DistributeLock;
import com.hnq.study.model.BaseRequest;
import com.hnq.study.model.BaseResponse;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

/**
 * @author henengqiang
 * @date 2019/11/07
 */
@RestController
@RequestMapping("/weblog")
public class WebLogTestController {

    @GetMapping("/get/hello")
    @ControllerWebLog(name = "get-hello", intoDb = true)
    public String hello(@RequestParam("name") String name) {
        return "hello " + name + "！";
    }

    @PostMapping("/post/hi")
    @ControllerWebLog(name = "post-hi", intoDb = true)
    @DistributeLock(key = "post_test", timeout = 10)
    public BaseResponse hi(@RequestBody @Valid BaseRequest baseRequest, BindingResult bindingResult) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return BaseResponse.addResult();
    }

}
