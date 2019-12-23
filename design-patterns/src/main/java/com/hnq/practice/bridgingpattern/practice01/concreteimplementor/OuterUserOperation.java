package com.hnq.practice.bridgingpattern.practice01.concreteimplementor;

import com.google.common.collect.Maps;
import com.hnq.practice.bridgingpattern.practice01.implementor.IUserOperation;

import java.util.Map;

/**
 * OuterUserOperation 类是外部用户操作类，实现了 IUserOperation 接口。
 * 对应于桥接模式的参与者，OuterUserOperation 是具体实现类 ConcreteImplementor。
 *
 * @author henengqiang
 * @date 2019/03/28
 */
public class OuterUserOperation implements IUserOperation {

    /**
     * 用户字典
     */
    private Map<String, String> userMap = Maps.newHashMap();

    /**
     * 用户到组织字典
     * key: userId
     * value: orgId
     */
    private Map<String, String> userToOrgMap = Maps.newHashMap();

    @Override
    public void addUser(String id, String name) {
        System.out.println("insert into outer_user (id, name) values(" + id + ", " + name + ");");
        userMap.put(id, name);
    }

    @Override
    public void addUserToOrg(String userId, String orgId) {
        System.out.println("update outer_user set org=" + orgId + " where id=" + userId + ";");
        userToOrgMap.put(userId, orgId);
    }

    @Override
    public String getUserName(String id) {
        System.out.println("select name from outer_user where id=" + id + ";");
        return userMap.get(id);
    }

    @Override
    public String getOrgOfUser(String userId) {
        System.out.println("select org from outer_user where id=" + userId + ";");
        return userToOrgMap.get(userId);
    }
}
