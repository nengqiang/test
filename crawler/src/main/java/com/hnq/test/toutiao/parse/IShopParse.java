package com.hnq.test.toutiao.parse;

import com.hnq.test.toutiao.model.UserShop;

import java.util.List;

/**
 * @author henengqiang
 * @date 2019/08/01
 */
public interface IShopParse {

    /**
     * 解析userId
     * @param pageContent
     * @return 报异常等会返回null
     */
    List<String> parseToutiaoUserId(String pageContent);

    /**
     * 解析shopId
     * @param pageContent
     * @return 没有或其他情况返回null
     */
    String parseUserShopId(String pageContent);

    /**
     * 解析userShop
     * @param pageContent
     * @return  没有或其他情况返回null
     */
    UserShop parseUserShop(String pageContent);

}
