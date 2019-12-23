package com.hnq.study.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author henengqiang
 * @date 2019/05/08
 */
public class StrUtils {

    /**
     * 把'"'替换为'\"'
     */
    public static String replaceColon(String str) {
        StringBuilder result = new StringBuilder();
        char[] temp = str.toCharArray();
        for (char c : temp) {
            if (c == '"') {
                result.append("\\");
            }
            result.append(c);
        }
        return result.toString();
    }

    /**
     * 把'\"'替换为'"'
     */
    public static String replaceColonAndSlash(String str) {
        StringBuilder result = new StringBuilder();
        char[] temp = str.toCharArray();
        for (char c : temp) {
            if (c == '\\') {
                continue;
            }
            result.append(c);
        }
        return result.toString();
    }

    /**
     * 将emoji表情替换成*
     * 过滤的方式很简单，直接使用正则表达式匹配编码范围，然后替换就行了。
     */
    public static String filterEmoji(String source) {
        if (StringUtils.isNotBlank(source)) {
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");
        } else {
            return source;
        }
    }

}
