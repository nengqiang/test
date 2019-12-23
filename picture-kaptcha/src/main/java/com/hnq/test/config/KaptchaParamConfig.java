package com.hnq.test.config;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author henengqiang
 * @date 2019/02/14
 */
@Configuration
public class KaptchaParamConfig {

    private static Map<String, Object> configMap;

    @Value("${kaptcha.picNums}")
    private Integer picNums;

    @Value("${kaptcha.filePath}")
    private String filePath;

    @Value("${kaptcha.codeLength}")
    private Integer codeLength;

    @Value("${kaptcha.imageWidth}")
    private Integer imageWidth;

    @Value("${kaptcha.imageHeight}")
    private Integer imageHeight;

    @Value("${kaptcha.charsets}")
    private String charsets;

    @Value("${kaptcha.fontSize}")
    private Integer fontSize;

    @Value("${kaptcha.textproducer.font.color}")
    private String fontColor;

    @Value("${kaptcha.fontNames}")
    private String fontNames;

    @Value("${kaptcha.border}")
    private String border;

    @Value("${kaptcha.picStyle}")
    private String picStyle;

    @PostConstruct
    private void init() {
        configMap = Maps.newHashMap();
        configMap.put("picNums", this.picNums);
        configMap.put("filePath", this.filePath);
        configMap.put("codeLength", this.codeLength);
        configMap.put("imageWidth", this.imageWidth);
        configMap.put("imageHeight", this.imageHeight);
        configMap.put("charsets", this.charsets);
        configMap.put("fontSize", this.fontSize);
        configMap.put("fontColor", this.fontColor);
        configMap.put("fontNames", this.fontNames);
        configMap.put("border", this.border);
        configMap.put("picStyle", this.picStyle);
    }

    @Override
    public String toString() {
        return "KaptchaParamConfig{" +
                "picNums=" + picNums +
                ", filePath='" + filePath + '\'' +
                ", codeLength=" + codeLength +
                ", imageWidth=" + imageWidth +
                ", imageHeight=" + imageHeight +
                ", charsets='" + charsets + '\'' +
                ", fontSize=" + fontSize +
                ", fontColor='" + fontColor + '\'' +
                ", fontNames='" + fontNames + '\'' +
                ", border='" + border + '\'' +
                ", picStyle='" + picStyle + '\'' +
                '}';
    }

    public Integer getPicNums() {
        return (Integer) configMap.get("picNums");
    }

    public String getFilePath() {
        return (String) configMap.get("filePath");
    }

    public Integer getCodeLength() {
        return (Integer) configMap.get("codeLength");
    }

    public Integer getImageWidth() {
        return (Integer) configMap.get("imageWidth");
    }

    public Integer getImageHeight() {
        return (Integer) configMap.get("imageHeight");
    }

    public String getCharsets() {
        return (String) configMap.get("charsets");
    }

    public Integer getFontSize() {
        return (Integer) configMap.get("fontSize");
    }

    public String getFontColor() {
        return (String) configMap.get("fontColor");
    }

    public String getBorder() {
        return (String) configMap.get("border");
    }

    public String getPicStyle() {
        return (String) configMap.get("picStyle");
    }

    public List<String> getFontNames() {
        return Arrays.asList(((String) configMap.get("fontNames")).split(","));
    }
}
