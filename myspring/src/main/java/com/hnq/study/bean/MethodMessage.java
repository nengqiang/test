package com.hnq.study.bean;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author henengqiang
 * @date 2019/12/05
 */
public class MethodMessage {

    private List<Class> paramTypes;

    private List<String> paramNames;

    private String methodName;

    private Method method;

    @Override
    public String toString() {
        return "MethodMessage{" +
                "paramTypes=" + paramTypes +
                ", paramNames=" + paramNames +
                ", methodName='" + methodName + '\'' +
                ", method=" + method +
                '}';
    }

    public List<Class> getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(List<Class> paramTypes) {
        this.paramTypes = paramTypes;
    }

    public List<String> getParamNames() {
        return paramNames;
    }

    public void setParamNames(List<String> paramNames) {
        this.paramNames = paramNames;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
