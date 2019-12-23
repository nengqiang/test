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
public class PatchcaConfig {

    private static Map<String, Object> configMap;

    @Value("${patchca.picNums}")
    private Integer picNums;

    @Value("${patchca.filePath}")
    private String filePath;

    @Value("${patchca.codeLength}")
    private Integer codeLength;

    @Value("${patchca.imageWidth}")
    private Integer imageWidth;

    @Value("${patchca.imageHeight}")
    private Integer imageHeight;

    @Value("${patchca.charsets}")
    private String charsets;

    @Value("${patchca.minFontSize}")
    private Integer minFontSize;

    @Value("${patchca.maxFontSize}")
    private Integer maxFontSize;

    @Value("${patchca.fontNames}")
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
        configMap.put("minFontSize", this.minFontSize);
        configMap.put("maxFontSize", this.maxFontSize);
        configMap.put("fontNames", this.fontNames);
    }

    @Override
    public String toString() {
        return "PatchcaConfig{" +
                "picNums=" + picNums +
                ", filePath='" + filePath + '\'' +
                ", codeLength=" + codeLength +
                ", imageWidth=" + imageWidth +
                ", imageHeight=" + imageHeight +
                ", charsets='" + charsets + '\'' +
                ", minFontSize=" + minFontSize +
                ", maxFontSize=" + maxFontSize +
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

    public Integer getMinFontSize() {
        return (Integer) configMap.get("minFontSize");
    }

    public Integer getMaxFontSize() {
        return (Integer) configMap.get("maxFontSize");
    }

    public List<String> getFontNames() {
        return Arrays.asList(((String) configMap.get("fontNames")).split(","));
    }
}
