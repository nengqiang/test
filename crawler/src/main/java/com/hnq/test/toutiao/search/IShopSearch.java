package com.hnq.test.toutiao.search;

import java.util.List;

/**
 * @author henengqiang
 * @date 2019/08/01
 */
public interface IShopSearch {

    List<String> searchToutiaoUserFanIds(String randomUrl, String proxy, String userId, int offset, int count);

    List<String> searchUserShopId(List<String> userIds);

}
