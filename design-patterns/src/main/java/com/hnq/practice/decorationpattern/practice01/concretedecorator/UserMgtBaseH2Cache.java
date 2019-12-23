package com.hnq.practice.decorationpattern.practice01.concretedecorator;

import com.google.common.collect.Maps;
import com.hnq.practice.decorationpattern.practice01.component.IUserMgt;
import com.hnq.practice.decorationpattern.practice01.decorator.BaseUserMgtDecorator;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 *  UserMgtBaseH2Cache 是基于H2缓存的用户管理类，从 UserMgtDecorator 派生，实现了 IUserMgt 接口。
 * 对应于装饰模式的参与者，UserMgtBaseH2Cache 是具体装饰者 ConcreteDecorator。
 *
 * @author henengqiang
 * @date 2019/04/15
 */
public class UserMgtBaseH2Cache extends BaseUserMgtDecorator implements IUserMgt {

    /**
     * 用户字典，key为用户id，value为用户名
     */
    private static Map<String, String> userMap = Maps.newHashMap();

    public UserMgtBaseH2Cache(IUserMgt userMgt) {
        super(userMgt);
    }

    @Override
    public String getUserName(String id) {
        String name = getUserNameFromH2(id);
        if (StringUtils.isEmpty(name)) {
            name = super.getUserName(id);
            setUserH2(id, name);
        }
        return name;
    }

    private String getUserNameFromH2(String id) {
        if (userMap.containsKey(id)) {
            System.out.println("debug--从H2缓存中获取id为" + id + "的用户名");
            return userMap.get(id);
        }
        return null;
    }

    private void setUserH2(String id, String name) {
        userMap.put(id, name);
    }
}
