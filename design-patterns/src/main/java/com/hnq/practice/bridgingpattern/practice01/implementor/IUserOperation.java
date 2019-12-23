package com.hnq.practice.bridgingpattern.practice01.implementor;

/**
 * IUserOperation 是用户操作接口，声明了用户操作的各方法。对应于桥接模式的参与者，IUserOperation 是实现类接口 Implementor。
 *
 * @author henengqiang
 * @date 2019/03/28
 */
public interface IUserOperation {

    void addUser(String id, String name);

    void addUserToOrg(String userId, String orgId);

    String getUserName(String id);

    String getOrgOfUser(String userId);

}
