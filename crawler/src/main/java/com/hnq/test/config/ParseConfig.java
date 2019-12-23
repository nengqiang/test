package com.hnq.test.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hnq.toolkit.util.csv.MyCsvUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

/**
 * @author henengqiang
 * @date 2019/07/30
 */
public class ParseConfig {

    public static void main(String[] args) {
        try {
            dealService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void dealService() throws IOException {
        String configPath = "/Users/hanif/studyProjects/test/crawler/src/main/resources/config";
        Map<Integer, String> idToName = parseWebsiteId();
        if (MapUtils.isEmpty(idToName)) {
            return;
        }
        Map<Integer, String> idToType = parseWebsiteType();
        if (MapUtils.isEmpty(idToType)) {
            return;
        }
        String configStr = readConfig();
        if (StringUtils.isEmpty(configStr)) {
            return;
        }
        JSONArray array = JSON.parseArray(configStr);
        for (Object o : array) {
            JSONObject obj = (JSONObject) o;
            int websiteId = obj.getIntValue("websiteId");
            String search = obj.getString("searchConfig");
            String parse = obj.getString("extractorConfig");
            String basePath = configPath + "/" + idToType.get(websiteId) + "/" + idToName.get(websiteId).replaceAll("\\.", "_");
            if (StringUtils.isNotBlank(search)) {
                String searchPath = basePath + "_search_config.xml";
                File searchFile = new File(searchPath);
                FileUtils.writeStringToFile(searchFile, search, StandardCharsets.UTF_8);
            }
            String parsePath = basePath + "_extractor_config.xml";
            File parseFile = new File(parsePath);
            FileUtils.writeStringToFile(parseFile, parse, StandardCharsets.UTF_8);
        }
    }

    private static Map<Integer, String> parseWebsiteType() {
        try {
            Map<Integer, String> idToName = parseWebsiteId();
            if (MapUtils.isEmpty(idToName)) {
                return null;
            }
            Map<Integer, String> idToType = Maps.newHashMap();
            String filePath = "/Users/hanif/dsFiles/saas2/websiteType.csv";
            String data = MyCsvUtils.getJsonFromCsvFile(filePath);
            JSONArray array = JSON.parseArray(data);
            for (Object o : array) {
                JSONObject obj = (JSONObject) o;
                String websiteType = obj.getString("websiteType");
                String websiteName = obj.getString("websiteName");
                for (Map.Entry<Integer, String> entry : idToName.entrySet()) {
                    if (Objects.equals(entry.getValue(), websiteName
                            .replaceAll(".cn", "")
                            .replaceAll(".com", ""))) {
                        idToType.put(entry.getKey(), websiteType);
                    }
                }
            }
            return idToType;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Map<Integer, String> parseWebsiteId() {
        try {
            Map<Integer, String> idToName = Maps.newHashMap();
            String filePath = "/Users/hanif/dsFiles/saas2/t_website.csv";
            String data = MyCsvUtils.getJsonFromCsvFile(filePath);
            JSONArray array = JSON.parseArray(data);
            for (Object o : array) {
                JSONObject obj = (JSONObject) o;
                idToName.put(obj.getInteger("id"),
                        obj.getString("websiteName")
                                .replaceAll(".cn", "")
                                .replaceAll(".com", ""));
            }
            return idToName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String readConfig() {
        try {
            String filePath = "/Users/hanif/dsFiles/saas2/t_website_conf.csv";
            return MyCsvUtils.getJsonFromCsvFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
