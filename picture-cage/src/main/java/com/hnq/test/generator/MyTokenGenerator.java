package com.hnq.test.generator;

import com.github.cage.IGenerator;
import com.hnq.test.generate.CageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;

/**
 * generator 拓展，添加相关配置信息
 *
 * @author henengqiang
 * @date 2018/12/4
 */
@Component
public class MyTokenGenerator implements IGenerator<String> {

    @Autowired
    private CageConfig config;

    private int length;

    private String charsets;

    private Random r;

    @PostConstruct
    private void init() {
        this.length = config.getCodeLength();
        this.charsets = config.getCharsets();
        this.r = new Random();
    }

    public MyTokenGenerator() {}

    public MyTokenGenerator(int length, String charsets) {
        this.length = length;
        this.charsets = charsets;
    }

    @Override
    public String next() {
        StringBuilder sb = new StringBuilder();
        int len = charsets.length();
        for (int i = 0; i < length; i++) {
            sb.append(charsets.charAt(r.nextInt(len - 1)));
        }
        return sb.toString();
    }

}
