package com.hnq.study.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author henengqiang
 * @date 2019/06/04
 */
@Configuration
@Getter
public class Config {

    @Value("${net.host}")
    private String serverAddress;

    @Value("${net.port}")
    private Integer port;

}
