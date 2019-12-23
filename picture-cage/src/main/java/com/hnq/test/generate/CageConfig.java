package com.hnq.test.generate;

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
public class CageConfig {

    private static Map<String, Object> configMap;

    @Value("${cage.picNums}")
    private Integer picNums;

    @Value("${cage.filePath}")
    private String filePath;

    @Value("${cage.codeLength}")
    private Integer codeLength;

    @Value("${cage.imageWidth}")
    private Integer imageWidth;

    @Value("${cage.imageHeight}")
    private Integer imageHeight;

    @Value("${cage.charsets}")
    private String charsets;

    @Value("${cage.fontSize}")
    private Integer fontSize;

    @Value("${cage.fontNames}")
    private String fontNames;

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
        configMap.put("fontNames", this.fontNames);
    }

    @Override
    public String toString() {
        return "CageConfig{" +
                "picNums=" + picNums +
                ", filePath='" + filePath + '\'' +
                ", codeLength=" + codeLength +
                ", imageWidth=" + imageWidth +
                ", imageHeight=" + imageHeight +
                ", charsets='" + charsets + '\'' +
                ", fontSize=" + fontSize +
                ", fontNames='" + fontNames + '\'' +
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

    public List<String> getFontNames() {
        return Arrays.asList(((String) configMap.get("fontNames")).split(","));
    }
}
