package com.hnq.study.filter;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author henengqiang
 * @date 2018/10/25
 */
@Configuration
public class ConfigurationFilter {

    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        // 添加过滤器
        registrationBean.setFilter(new MyFilter());
        // 设置过滤路径，/*表示所有路径
        registrationBean.addUrlPatterns("/*");
        // 添加默认参数
        registrationBean.addInitParameter("name", "alice");
        registrationBean.setName("MyFilter");
        // 设置优先级
        registrationBean.setOrder(1);
        return registrationBean;
    }

}
