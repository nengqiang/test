package com.hnq.test.toutiao.parse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hnq.test.toutiao.model.UserShop;
import com.hnq.toolkit.util.JsonPathUtils;
import com.hnq.toolkit.util.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author henengqiang
 * @date 2019/08/01
 */
@Slf4j
public class ShopParseImpl implements IShopParse {

    private static final Pattern SHOP_ID_PATTERN = Pattern.compile("id=([a-zA-Z]{5,8})");

    private static final String CONTAINS_STR = "haohuo.snssdk.com/views/shop";

    private static final String CONTAINS_STR1 = "haohuo.jinritemai.com/views/shop";

    @Override
    public List<String> parseToutiaoUserId(String pageContent) {
        try {
            List<String> info = JsonPathUtils.readAsList(pageContent, "$.data.users[*].user.info");
            return info.stream().map(str -> String.valueOf(JSON.parseObject(str).getLong("user_id"))).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("解析UserId异常, pageContent={}", pageContent, e);
        }
        return null;
    }

    @Override
    public String parseUserShopId(String pageContent) {
        try {
            if (StringUtils.containsAny(pageContent, CONTAINS_STR, CONTAINS_STR1)) {
                return RegexUtils.group(pageContent, SHOP_ID_PATTERN, 1);
            }
        } catch (Exception e) {
            log.error("解析shopId异常, pageContent={}", pageContent, e);
        }
        return null;
    }

    @Override
    public UserShop parseUserShop(String pageContent) {
        try {
            if (StringUtils.containsAny(pageContent, CONTAINS_STR, CONTAINS_STR1)) {
                List<String> info = JsonPathUtils.readAsList(pageContent, "$.data.top_tab[*]");
                for (String str : info) {
                    if (StringUtils.containsAny(str,"店铺", "橱窗")) {
                        JSONObject obj = JSON.parseObject(str);
                        UserShop userShop = new UserShop();
                        userShop.setShopId(RegexUtils.group(str, SHOP_ID_PATTERN, 1));
                        userShop.setShopUrl(obj.getString("url"));
                        String userId = JsonPathUtils.readAsString(pageContent, "$.data.user_id");
                        userShop.setToutiaoId(userId);
                        userShop.setType(obj.getString("type"));
                        return userShop;
                    }
                }
            }
        } catch (Exception e) {
            log.error("解析userShop异常, pageContent={}", pageContent, e);
        }
        return null;
    }
}
