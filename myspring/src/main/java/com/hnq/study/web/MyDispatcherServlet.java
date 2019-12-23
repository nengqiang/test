package com.hnq.study.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author henengqiang
 * @date 2019/12/05
 * @see <a href="https://github.com/codeljy/myspring">Code</a>
 */
public class MyDispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setHeader("Content-type", "text/html;charset=UTF-8");
            resp.setCharacterEncoding("utf-8");
            // 反射执行
            String responseCode = ObjectInvoke.invoke(req, resp);
            // 404处理
            if ("404".equals(responseCode)) {
                PrintWriter writer = resp.getWriter();
                writer.write("404");
                writer.close();
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        // 类注解扫描
        ClassAnnotationReader.read();
        // 对象实例化
        ObjectInstantiates.instance(ClassAnnotationReader.classes);
        // aop代理对象生成
        AopProxyInstantiates.instance();
        // url与方法关联
        UrlMethodMapper.readUrl();
        // 对象注入
        ParamInsert.insert();
    }
}
