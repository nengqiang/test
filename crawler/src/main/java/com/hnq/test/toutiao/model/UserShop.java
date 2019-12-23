package com.hnq.test.toutiao.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author henengqiang
 * @date 2019/08/02
 */
@Data
public class UserShop implements Serializable {

    /**
     * 店铺id
     */
    private String shopId;

    /**
     * 店铺链接
     */
    private String shopUrl;

    /**
     * 店铺类型
     */
    private String type;

    /**
     * 店铺id对应的头条id
     */
    private String toutiaoId;

}
