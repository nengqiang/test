package com.hnq.practice.bridgingpattern.practice01.concreteimplementor;

import com.google.common.collect.Maps;
import com.hnq.practice.bridgingpattern.practice01.implementor.IUserOperation;

import java.util.Map;

/**
 * InnerUserOperation 类是内部用户操作类，实现了 IUserOperation 接口。
 * 对应于桥接模式的参与者，InnerUserOperation 是具体实现类 ConcreteImplementor。
 *
 * @author henengqiang
 * @date 2019/03/28
 */
public class InnerUserOperation implements IUserOperation {

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
        System.out.println("insert into innerUser (id, name) values(" + id + ", " + name + ");");
        userMap.put(id, name);
    }

    @Override
    public void addUserToOrg(String userId, String orgId) {
        System.out.println("update innerUser set org=" + orgId + " where id=" + userId + ";");
        userToOrgMap.put(userId, orgId);
    }

    @Override
    public String getUserName(String id) {
        System.out.println("select name from innerUser where id=" + id + ";");
        return userMap.get(id);
    }

    @Override
    public String getOrgOfUser(String userId) {
        System.out.println("select org from innerUser where id=" + userId + ";");
        return userToOrgMap.get(userId);
    }

}
