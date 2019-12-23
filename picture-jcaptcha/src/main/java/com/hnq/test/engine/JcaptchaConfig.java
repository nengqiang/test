package com.hnq.test.engine;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author henengqiang
 * @date 2019/02/13
 */
@Configuration
public class JcaptchaConfig {

    private static Map<String, Object> configMap;

    @Value("${jcaptcha.picNums}")
    private Integer picNums;

    @Value("${jcaptcha.filePath}")
    private String filePath;

    @Value("${jcaptcha.minWordLength}")
    private Integer minWordLength;

    @Value("${jcaptcha.maxWordLength}")
    private Integer maxWordLength;

    @Value("${jcaptcha.minFontSize}")
    private Integer minFontSize;

    @Value("${jcaptcha.maxFontSize}")
    private Integer maxFontSize;

    @Value("${jcaptcha.imageWidth}")
    private Integer imageWidth;

    @Value("${jcaptcha.imageHeight}")
    private Integer imageHeight;

    @Value("${jcaptcha.acceptedChars}")
    private String acceptedChars;

    @Value("${jcaptcha.fontNames}")
    private String fontNames;

    @PostConstruct
    private void init() {
        configMap = Maps.newHashMap();
        configMap.put("picNums", this.picNums);
        configMap.put("filePath", this.filePath);
        configMap.put("minWordLength", this.minWordLength);
        configMap.put("maxWordLength", this.maxWordLength);
        configMap.put("imageWidth", this.imageWidth);
        configMap.put("imageHeight", this.imageHeight);
        configMap.put("acceptedChars", this.acceptedChars);
        configMap.put("minFontSize", this.minFontSize);
        configMap.put("maxFontSize", this.maxFontSize);
        configMap.put("fontNames", this.fontNames);
    }

    public Integer getPicNums() {
        return (Integer) configMap.get("picNums");
    }

    public String getFilePath() {
        return (String) configMap.get("filePath");
    }

    public Integer getMinWordLength() {
        return (Integer) configMap.get("minWordLength");
    }

    public Integer getMaxWordLength() {
        return (Integer) configMap.get("maxWordLength");
    }

    public Integer getMinFontSize() {
        return (Integer) configMap.get("minFontSize");
    }

    public Integer getMaxFontSize() {
        return (Integer) configMap.get("maxFontSize");
    }

    public Integer getImageWidth() {
        return (Integer) configMap.get("imageWidth");
    }

    public Integer getImageHeight() {
        return (Integer) configMap.get("imageHeight");
    }

    public String getAcceptedChars() {
        return (String) configMap.get("acceptedChars");
    }

    public List<String> getFontNames() {
        return Arrays.asList(((String) configMap.get("fontNames")).split(","));
    }
}

