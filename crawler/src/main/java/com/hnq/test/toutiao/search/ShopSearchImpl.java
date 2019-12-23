package com.hnq.test.toutiao.search;

import com.hnq.test.toutiao.common.Constants;
import com.hnq.test.toutiao.parse.IShopParse;
import com.hnq.test.toutiao.parse.ShopParseImpl;
import com.hnq.toolkit.util.HttpUtils;
import com.hnq.toolkit.util.StrUtils;
import com.hnq.toolkit.util.http.MoreHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author henengqiang
 * @date 2019/08/01
 */
@Slf4j
public class ShopSearchImpl implements IShopSearch {

    private IShopParse shopParse = new ShopParseImpl();

    @Override
    public List<String> searchToutiaoUserFanIds(String randomUrl, String proxy, String heId, int offset, int count) {
        List<String> userIds = null;
        List<String> list = null;
        try {
            String url = StrUtils.format(randomUrl, heId, offset, count);
            log.debug("userIdList requestUrl={}", url);
            MoreHttpRequest request = MoreHttpRequest.get(url).setUserAgent(Constants.randomUerAgent()).setProxy(proxy);
            String pageContent = HttpUtils.sendRequest(request, String.class, 3);
            userIds = shopParse.parseToutiaoUserId(pageContent);
        } catch (Exception e) {
            log.error("搜索userId异常, offset={}", offset, e);
        }
        if (CollectionUtils.isNotEmpty(userIds)) {
            list = userIds.stream().distinct().collect(Collectors.toList());
            log.info("爬取到用户[{}]粉丝列表, size={}", heId, list.size());
        }
        return list;
    }

    @Override
    public List<String> searchUserShopId(List<String> userIds) {
        List<String> shopIds = Lists.newArrayList();
        String userInfoUrl = Constants.randomUserUrl();
        try {
            for (String userId : userIds) {
                String url = StrUtils.format(userInfoUrl, userId);
                MoreHttpRequest request = MoreHttpRequest.get(url).setUserAgent(Constants.randomUerAgent());
                String pageContent = HttpUtils.sendRequest(request, String.class, 3);
                String shopId = shopParse.parseUserShopId(pageContent);
                if (StringUtils.isNotEmpty(shopId)) {
                    shopIds.add(shopId);
                }
            }
        } catch (Exception e) {
            log.error("搜索shopId异常, userIds={}", userIds, e);
        }
        if (shopIds.size() > 0) {
            log.info(">>>>>>>>>>>>>>>>>>>>搜寻到shopIds={}", shopIds);
        } else {
            log.info("<<<<<<<<<<<<<<<<<<<<没有搜寻到shopId");
        }
        return shopIds;
    }

}
