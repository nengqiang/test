package com.hnq.study.action;

import com.hnq.study.anno.MyAutowired;
import com.hnq.study.anno.MyController;
import com.hnq.study.anno.MyRequestMapping;
import com.hnq.study.anno.MyRequestParam;
import com.hnq.study.service.IDemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author henengqiang
 * @date 2019/12/02
 */
@MyController
@MyRequestMapping("/demo")
public class DemoAction {

    @MyAutowired
    private IDemoService demoService;

    @MyRequestMapping("/query")
    public void query(HttpServletRequest req, HttpServletResponse res, @MyRequestParam("name") String name) {
        String result = demoService.getName(name);
        try {
            res.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
