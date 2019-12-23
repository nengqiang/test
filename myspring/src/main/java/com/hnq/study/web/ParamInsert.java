package com.hnq.study.web;

import com.hnq.study.annotation.MyAutowired;
import com.hnq.study.annotation.MyResource;
import com.hnq.study.bean.BeanMessage;
import com.hnq.study.factory.BeanFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

/**
 * @author henengqiang
 * @date 2019/12/05
 */
public class ParamInsert {

    /**
     * 为对象的成员变量注入对象
     */
    public static void insert() {
        try {
            for (BeanMessage beanMessage : BeanFactory.getInstance().getBeans()) {
                Object obj = beanMessage.getBean();
                Class clazz = obj.getClass();
                Field[] fields = clazz.getDeclaredFields();
                for (Field f : fields) {
                    f.setAccessible(true);
                    if (f.isAnnotationPresent(MyAutowired.class)) {
                        beanInsert(obj, f, f.getType().getName());
                    } else if (f.isAnnotationPresent(MyResource.class)) {
                        MyResource resource = f.getDeclaredAnnotation(MyResource.class);
                        String value = resource.value();
                        if (!StringUtils.isEmpty(value)) {
                            String n = resource.name();
                            if (!StringUtils.isEmpty(n)) {
                                beanInsert(obj, f, n);
                            } else {
                                beanInsert(obj, f, f.getType().getName());
                            }
                        } else {
                            beanInsert(obj, f, value);
                        }
                    }
                }

            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void beanInsert(Object obj, Field f, String name) throws IllegalAccessException {
        BeanMessage beanMessage = BeanFactory.getInstance().gainBean(name);
        f.set(obj, beanMessage.gainObject());
    }

}
