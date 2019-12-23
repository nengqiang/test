package com.hnq.study.web;

import com.hnq.study.annotation.MyAop;
import com.hnq.study.aop.Aop;
import com.hnq.study.bean.BeanMessage;
import com.hnq.study.factory.BeanFactory;
import com.hnq.study.proxy.SonProxy;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author henengqiang
 * @date 2019/12/05
 */
public class AopProxyInstantiates {

    /**
     * aop对象的实例化
     */
    public static void instance() {
        BeanFactory.getInstance().getBeans().stream().map(BeanMessage::getBean).forEach(obj -> {
            Method[] methods = obj.getClass().getDeclaredMethods();
            Arrays.stream(methods).filter(method -> method.isAnnotationPresent(MyAop.class)).forEach(method -> process(obj, method));
            if (obj.getClass().isAnnotationPresent(MyAop.class)) {
                process(obj, null);
            }
        });
    }

    /**
     * aop代理对象反向生成
     */
    private static void process(final Object obj, final Method method) {
        MyAop aopAnno;
        if (method != null) {
            aopAnno = method.getAnnotation(MyAop.class);
        } else {
            aopAnno = obj.getClass().getAnnotation(MyAop.class);
        }
        String aopBeanName = aopAnno.aopBeanName();
        final String aopType = aopAnno.aopType();
        BeanMessage beanMessage = BeanFactory.getInstance().gainBean(aopBeanName);
        final Aop aopBean = (Aop) beanMessage.getBean();
        Object proxy = new SonProxy(obj) {
            @Override
            public Object intercept(Object o, Method m, Object[] objects, MethodProxy methodProxy) throws Throwable {
                if (method != null && m.getName().equals(method.getName())) {
                    Object invoke = getObject(m, objects);
                    if (invoke != null) {
                        return invoke;
                    }
                } else {
                    Object invoke = getObject(m, objects);
                    if (invoke != null) {
                        return invoke;
                    }
                }
                return m.invoke(obj, objects);
            }

            @SuppressWarnings("unchecked")
            private Object getObject(Method m, Object[] objs) throws InvocationTargetException, IllegalAccessException {
                switch (aopType) {
                    case MyAop.pro:
                        aopBean.pro();
                        return m.invoke(obj, objs);
                    case MyAop.after: {
                        Object invoke = m.invoke(obj, objs);
                        return aopBean.after(invoke);
                    }
                    case MyAop.around: {
                        aopBean.pro();
                        Object invoke = m.invoke(obj, objs);
                        return aopBean.after(invoke);
                    }
                }
                return null;
            }
        }.getProxy();
        // 设置代理对象
        BeanFactory.getInstance().gainBean(obj.getClass().getName()).setAop(proxy);
    }

}
