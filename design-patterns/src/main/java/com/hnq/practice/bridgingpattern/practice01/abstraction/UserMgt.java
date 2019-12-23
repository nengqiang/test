package com.hnq.practice.bridgingpattern.practice01.abstraction;

import com.hnq.practice.bridgingpattern.practice01.implementor.IUserOperation;

/**
 * UserMgt 是用户管理类，声明了“注册用户”、“获取用户信息”等方法。
 * 对应于桥接模式的参与者，UserMgt是我们的抽象类 Abstraction。
 *
 * @author henengqiang
 * @date 2019/03/28
 */
public class UserMgt {

    public String userId1 = "001";

    private IUserOperation userOperation;

    public UserMgt(IUserOperation userOperation) {
        this.userOperation = userOperation;
    }

    public void registerUser(String userId, String userName, String orgId) {
        userOperation.addUser(userId, userName);
        userOperation.addUserToOrg(userId, orgId);
    }

    public String getUserInfo(String userId) {
        return String.format("用户id：%s；用户姓名：%s；所属组织id：%s；",
                userId, userOperation.getUserName(userId), userOperation.getOrgOfUser(userId));
    }

}
