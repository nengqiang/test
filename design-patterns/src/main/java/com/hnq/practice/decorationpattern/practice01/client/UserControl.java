package com.hnq.practice.decorationpattern.practice01.client;

import com.hnq.practice.decorationpattern.practice01.component.IUserMgt;
import com.hnq.practice.decorationpattern.practice01.concretecomponent.UserMgt;
import com.hnq.practice.decorationpattern.practice01.concretedecorator.UsaerMgtBaseRedisCache;
import com.hnq.practice.decorationpattern.practice01.concretedecorator.UserMgtBaseH2Cache;

/**
 * UserControl 类是用户控制器类，对应于装饰模式的参与者，UserControl 是客户Client。
 *
 * @author henengqiang
 * @date 2019/04/15
 */
public class UserControl {

    public static String userId = "001";

    public static String cacheTypeH2 = "H2";

    public static String cacheTypeRedis = "Redis";

    private String cacheType;

    public String getCacheType() {
        return cacheType;
    }

    public void setCacheType(String cacheType) {
        this.cacheType = cacheType;
    }

    public void outputUserInfo(String id) {
        IUserMgt userMgt = getUserMgt();
        if (getCacheType().equals(cacheTypeH2)) {
            userMgt = new UserMgtBaseH2Cache(userMgt);
        } else if (getCacheType().equals(cacheTypeRedis)) {
            userMgt = new UsaerMgtBaseRedisCache(userMgt);
        }
        System.out.println("userName: " + userMgt.getUserName(id));
    }

    private IUserMgt getUserMgt() {
        return new UserMgt();
    }
}
