package com.hnq.study.bean;

/**
 * @author henengqiang
 * @date 2019/12/05
 */
public class UrlMethodRelate {

    private String url;

    private String className;

    private String methodName;

    @Override
    public String toString() {
        return "UrlMethodRelate{" +
                "url='" + url + '\'' +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
