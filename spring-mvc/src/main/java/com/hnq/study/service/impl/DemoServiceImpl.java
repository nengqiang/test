package com.hnq.study.service.impl;

import com.hnq.study.anno.MyService;
import com.hnq.study.service.IDemoService;

/**
 * @author henengqiang
 * @date 2019/12/02
 */
@MyService
public class DemoServiceImpl implements IDemoService {

    @Override
    public String getName(String name) {
        return "My name is " + name;
    }

}
