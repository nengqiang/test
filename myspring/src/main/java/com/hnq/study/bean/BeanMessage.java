package com.hnq.study.bean;

import java.util.List;
import java.util.Objects;

/**
 * @author henengqiang
 * @date 2019/12/05
 */
public class BeanMessage {

    public static final String CONTROLLER = "controller";

    public static final String SERVICE = "service";

    public static final String AOP = "aop";

    private String name;

    private String className;

    private String beanType;

    private Object aop;

    private Object bean;

    private List<MethodMessage> methods;

    public Object gainObject() {
        return gainInvokeObj();
    }

    public Object gainInvokeObj() {
        return aop == null ? bean : aop;
    }

    public BeanMessage gainBeanMessage(String target) {
        if (Objects.equals(name, target)) {
            return this;
        }
        if (Objects.equals(className, target)) {
            return this;
        }
        return null;
    }

    public boolean isController() {
        return CONTROLLER.equals(this.beanType);
    }

    public boolean isService() {
        return SERVICE.equals(this.beanType);
    }

    public boolean isAop() {
        return AOP.equals(this.beanType);
    }

    public MethodMessage gainMethod(String methodName) {
        return methods.stream().filter(m -> m.getMethodName().equals(methodName)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "BeanMessage{" +
                "name='" + name + '\'' +
                ", className='" + className + '\'' +
                ", beanType='" + beanType + '\'' +
                ", aop=" + aop +
                ", bean=" + bean +
                ", methods=" + methods +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getBeanType() {
        return beanType;
    }

    public void setBeanType(String beanType) {
        this.beanType = beanType;
    }

    public Object getAop() {
        return aop;
    }

    public void setAop(Object aop) {
        this.aop = aop;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public List<MethodMessage> getMethods() {
        return methods;
    }

    public void setMethods(List<MethodMessage> methods) {
        this.methods = methods;
    }
}
