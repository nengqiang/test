package com.hnq.test.function;

import lombok.extern.slf4j.Slf4j;
import org.ansj.library.DATDictionary;
import org.ansj.util.MyStaticValue;
import org.apache.commons.lang3.StringUtils;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.domain.Value;
import org.nlpcn.commons.lang.tire.library.Library;
import org.nlpcn.commons.lang.util.IOUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author henengqiang
 * @date 2019/09/25
 */
@Slf4j
public class KeywordLibrary {

    private static final String DEFAULT_NATURE = "userDefine";

    private static final String DEFAULT_FREQ_STR = "1000";

    /**
     * 加载词典
     * @param forest
     * @param path      词典路径/目录 后缀必须为".dic"
     */
    public static void loadLibrary(Forest forest, String path) {
        if (path != null) {
            File file = new File(path);
            if (!file.canRead() || file.isHidden()) {
                log.warn("init user library warning: file in path {} not found or failed to read!", file.getAbsolutePath());
                return;
            }
            if (!file.exists()) {
                file = new File(path);
            }
            if (file.isFile()) {
                loadFile(forest, file);
            } else if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    Arrays.stream(files).filter(f -> f.getName().trim().endsWith(".dic")).forEach(f -> loadFile(forest, f));
                }
            } else {
                log.warn("init user library error: file in path {} not found that file!", file.getAbsolutePath());
            }
        }
    }

    /**
     * 单个文件加载词典
     */
    public static void loadFile(Forest forest, File file) {
        if (!file.canRead()) {
            log.warn("file in path {} can not be read!", file.getAbsolutePath());
        }
        String temp;
        BufferedReader br = null;
        String[] strArr;
        Value value;
        try {
            br = IOUtil.getReader(new FileInputStream(file), StandardCharsets.UTF_8.name());
            while ((temp = br.readLine()) != null) {
                if (StringUtils.isNotBlank(temp)) {
                    strArr = temp.split("\t");
                    strArr[0] = strArr[0].toLowerCase();
                    // 如果核心辞典存在 就放弃
                    if (MyStaticValue.isSkipUserDefine && DATDictionary.getId(strArr[0]) > 0) {
                        continue;
                    }
                    if (strArr.length != 3) {
                        value = new Value(strArr[0], DEFAULT_NATURE, DEFAULT_FREQ_STR);
                    } else {
                        value = new Value(strArr[0], strArr[1], strArr[2]);
                    }
                    Library.insertWord(forest, value);
                }
            }
            log.info("init user library ok! path: {}", file.getAbsolutePath());
        } catch (Exception e) {
            log.error("init user library error: ", e);
        } finally {
            IOUtil.close(br);
        }
    }

}
