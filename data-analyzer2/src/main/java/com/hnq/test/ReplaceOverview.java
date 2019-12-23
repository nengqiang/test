package com.hnq.test;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hnq.toolkit.util.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author henengqiang
 * @date 2019/11/28
 */
public class ReplaceOverview {

    public static void main(String[] args) {
        try {
//            Map<String, String> originalData = readOriginalData();
//            replaceTrainData(originalData);
            testResult();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, String> readOriginalData() throws IOException {
        InputStream in = ReplaceOverview.class.getClassLoader().getResourceAsStream("data/origin/gfd4.json");
        String data = IOUtils.toString(Objects.requireNonNull(in), Charsets.UTF_8);
        List<Map> maps = JSON.parseArray(data, Map.class);

        Map<String, String> result = Maps.newHashMap();
        for (Map map : maps) {
            String key = map.get("title") + "###";
            if (StringUtils.isNotEmpty(map.get("appeal").toString())) {
                key += map.get("appeal") + "###";
            }
            if (map.get("overview").toString().length() <= 15) {
                key += map.get("overview");
            } else {
                key += map.get("overview").toString().substring(0, 15);
            }

            String value = map.get("title") + "\n";
            if (StringUtils.isNotEmpty(map.get("appeal").toString())) {
                value += map.get("appeal") + "\n";
            }
            value += map.get("overview");
            result.put(key, value);
        }
        return result;
    }

    private static void replaceTrainData(Map<String, String> replaceData) throws IOException {
        List<String> listFileName = Lists.newArrayList();
        FileUtils.getAllFileName("/Users/hanif/studyProjects/test/data-analyzer2/src/main/resources/data/train", listFileName);
        listFileName = listFileName.stream().filter(f -> f.endsWith("txt")).collect(Collectors.toList());
        int count = 0;
        for (String path : listFileName) {
            String trainData = org.apache.commons.io.FileUtils.readFileToString(new File(path), Charsets.UTF_8);
            String[] temp = trainData.split("\\n");
            String key = StringUtils.EMPTY;
            if (temp.length == 3) {
                if (temp[2].length() <= 15) {
                    key = temp[0] + "###" + temp[1] + "###" + temp[2];
                } else {
                    key = temp[0] + "###" + temp[1] + "###" + temp[2].substring(0, 15);
                }
            } else if (temp.length == 2) {
                if (temp[1].length() <= 15) {
                    key = temp[0] + "###" + temp[1];
                } else {
                    key = temp[0] + "###" + temp[1].substring(0, 15);
                }
            }
            String replaceStr = replaceData.get(key);
            if (replaceStr != null) {
                org.apache.commons.io.FileUtils.writeStringToFile(new File(path), replaceStr, Charsets.UTF_8);
                count++;
                System.err.println("写入第【" + count + "】份数据");
            }
        }
    }

    private static void testResult() throws IOException {
        List<String> listFileName = Lists.newArrayList();
        FileUtils.getAllFileName("/Users/hanif/studyProjects/test/data-analyzer2/src/main/resources/data/train", listFileName);
        listFileName = listFileName.stream().filter(f -> f.endsWith("txt")).collect(Collectors.toList());
        int count = 0;
        for (String path : listFileName) {
            String trainData = org.apache.commons.io.FileUtils.readFileToString(new File(path), Charsets.UTF_8);
            if (StringUtils.isEmpty(trainData)) {
                count++;
            }
        }
        System.out.println(count);
    }

}
