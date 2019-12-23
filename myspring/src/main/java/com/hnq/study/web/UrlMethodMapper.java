package com.hnq.study.web;

import com.hnq.study.annotation.MyRequestMapping;
import com.hnq.study.bean.BeanMessage;
import com.hnq.study.bean.UrlMethodRelate;
import com.hnq.study.factory.BeanFactory;
import com.hnq.study.factory.UrlFactory;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author henengqiang
 * @date 2019/12/05
 */
public class UrlMethodMapper {

    /**
     * 建立url与方法的映射关系
     */
    public static void readUrl() {
        for (BeanMessage beanMessage : BeanFactory.getInstance().getBeans()) {
            Object obj = beanMessage.getBean();
            if (obj.getClass().isAnnotationPresent(MyRequestMapping.class)) {
                MyRequestMapping requestMapping = obj.getClass().getAnnotation(MyRequestMapping.class);
                String beanUrl = requestMapping.value();
                urlRelateMethodProcess(obj, beanUrl);
            } else {
                urlRelateMethodProcess(obj, "");
            }
        }
    }

    private static void urlRelateMethodProcess(Object obj, String beanUrl) {
        Method[] methods = obj.getClass().getDeclaredMethods();
        Arrays.stream(methods).filter(m -> m.isAnnotationPresent(MyRequestMapping.class)).forEach(method -> {
            MyRequestMapping requestMapping = method.getAnnotation(MyRequestMapping.class);
            String methodUrl = requestMapping.value();
            String url = (beanUrl + methodUrl).replaceAll("//", "/");
            UrlMethodRelate urlMethodRelate = new UrlMethodRelate();
            urlMethodRelate.setUrl(url);
            urlMethodRelate.setClassName(obj.getClass().getName());
            urlMethodRelate.setMethodName(method.getName());
            UrlFactory.getInstance().addUrlMethodRelate(urlMethodRelate);
        });
    }

}
