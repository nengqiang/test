package com.hnq.study.web;

import com.alibaba.fastjson.JSON;
import com.hnq.study.bean.BeanMessage;
import com.hnq.study.bean.MethodMessage;
import com.hnq.study.bean.UrlMethodRelate;
import com.hnq.study.factory.BeanFactory;
import com.hnq.study.factory.UrlFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author henengqiang
 * @date 2019/12/05
 */
public class ObjectInvoke {

    public static String invoke(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String url = req.getRequestURI();
            if (url.lastIndexOf("?") != -1) {
                url = url.substring(0, url.lastIndexOf("?"));
            }
            UrlMethodRelate relate = UrlFactory.getInstance().gainUrlMethodRelate(url);
            if (relate == null) {
                return "404 Not Found!";
            }

            BeanMessage beanMessage = BeanFactory.getInstance().gainBean(relate.getClassName());
            Object obj = beanMessage.gainInvokeObj();
            MethodMessage mm = beanMessage.gainMethod(relate.getMethodName());
            Method method = mm.getMethod();

            List<String> paramNames = mm.getParamNames();
            Map<String, Object> params = paramNames.stream().collect(Collectors.toMap(
                    paramName -> paramName, req::getParameter, (a, b) -> b, LinkedHashMap::new));

            Object result = method.invoke(obj, params.values().toArray());
            String respString = obj.getClass().getName() + "." + method.getName() + " 返回NULL";
            if (result != null) {
                respString = result instanceof String ? result.toString() : JSON.toJSONString(result);
            }

            PrintWriter pw = resp.getWriter();
            pw.write(respString);
            pw.close();
            return "200";
        } catch (Exception e) {
            e.printStackTrace();
            return "500";
        }
    }

}
