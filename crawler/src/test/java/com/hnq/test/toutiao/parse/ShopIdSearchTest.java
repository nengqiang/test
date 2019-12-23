package com.hnq.test.toutiao.parse;

import com.google.common.collect.Lists;
import com.hnq.test.toutiao.ShopIdSearchService;
import com.hnq.test.toutiao.search.IShopSearch;
import com.hnq.test.toutiao.search.ShopSearchImpl;
import com.hnq.toolkit.util.JsonPathUtils;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author henengqiang
 * @date 2019/08/01
 */
public class ShopIdSearchTest {

    @Test
    public void jsonParseTest() throws IOException {
        String filePath = "/Users/hanif/studyProjects/test/crawler/src/test/resources/toutiao/userList.json";
        File file = new File(filePath);
        String data = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        List<String> result = JsonPathUtils.readAsList(data, "$.data.users[*].user.info.user_id");
        result.forEach(System.out::println);
        System.err.println(result.size());
    }

    @Test
    public void parseUserShopIdTest() throws IOException {
        String filePath = "/Users/hanif/studyProjects/test/crawler/src/test/resources/toutiao/userInfo.json";
        File file = new File(filePath);
        String data = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        IShopParse shopParse = new ShopParseImpl();
        String result = shopParse.parseUserShopId(data);
        System.out.println(result);
    }

    @Test
    public void searchUserShopIdTest() {
        List<String> userIds = Lists.newArrayList("4098955063");
        IShopSearch shopSearch = new ShopSearchImpl();
        List<String> result = shopSearch.searchUserShopId(userIds);
        result.forEach(System.out::println);
    }

    @Test
    public void searchAndSaveShopIdTest() {
        List<String> userIds = Lists.newArrayList("4098955063");
        ShopIdSearchService shopSearch = new ShopIdSearchService();
        shopSearch.searchAndSaveShopId(userIds);
    }

    @Test
    public void executeSearchTest() {
        ShopIdSearchService shopIdSearchService = new ShopIdSearchService();
        shopIdSearchService.searchUserIdAndShopId();
    }

}