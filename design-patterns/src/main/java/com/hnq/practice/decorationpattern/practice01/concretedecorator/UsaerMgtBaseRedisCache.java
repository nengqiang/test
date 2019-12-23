package com.hnq.practice.decorationpattern.practice01.concretedecorator;

import com.google.common.collect.Maps;
import com.hnq.practice.decorationpattern.practice01.component.IUserMgt;
import com.hnq.practice.decorationpattern.practice01.decorator.BaseUserMgtDecorator;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 *  UserMgtBaseRedisCache 是基于 Redis 缓存的用户管理类，从 UserMgtDecorator 派生，实现了 IUserMgt 接口。
 * 对应于装饰模式的参与者，UserMgtBaseRedisCache 是具体装饰者 ConcreteDecorator。
 *
 * @author henengqiang
 * @date 2019/04/15
 */
public class UsaerMgtBaseRedisCache extends BaseUserMgtDecorator implements IUserMgt {

    /**
     * 用户字典，key为用户id，value为用户名
     */
    private static Map<String, String> userMap = Maps.newHashMap();

    public UsaerMgtBaseRedisCache(IUserMgt userMgt) {
        super(userMgt);
    }

    @Override
    public String getUserName(String id) {
        String name = getUserNameFromRedis(id);
        if (StringUtils.isEmpty(name)) {
            name = super.getUserName(id);
            setUserRedis(id, name);
        }
        return name;
    }

    private String getUserNameFromRedis(String id) {
        if (userMap.containsKey(id)) {
            System.out.println("debug--从Redis缓存中获取id为" + id + "的用户名");
            return userMap.get(id);
        }
        return null;
    }

    private void setUserRedis(String id, String name) {
        userMap.put(id, name);
    }
}
