package com.hnq.study.handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author henengqiang
 * @date 2018/10/25
 */
public class MyInterceptor1 implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        System.out.println("MyInterceptor1 ==> 在处理请求之前调用（Controller方法调用之前）");
        // 只有返回true才会继续向下执行，返回false取消当前请求
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj,
        ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor1 ==> 请求处理之后调用，但是在视图渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e)
        throws Exception {
        System.out.println("MyInterceptor1 ==> 在整个请求结束之后被调用，也就是在DispatcherServlet渲染了对应的视图之后执行" +
                "（主要是用于进行资源清理工作）");
    }

}
