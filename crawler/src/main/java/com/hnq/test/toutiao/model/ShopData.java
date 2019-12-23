package com.hnq.test.toutiao.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author henengqiang
 * @date 2019/07/26
 */
@Data
public class ShopData implements Serializable {
    private String queryShopId;
    private String shopId;
    private String shopName;
    private String companyName;
    private String shopUrl;
    private String createTime;
}
