package com.hnq.practice.adapterpattern.practice01.client;

import com.hnq.practice.adapterpattern.practice01.targetinterface.IUserMgt;

/**
 * @author henengqiang
 * @date 2019/03/27
 */
public class UserRegister {

    private IUserMgt userMgt;

    public UserRegister(IUserMgt userMgt) {
        this.userMgt = userMgt;
    }

    public void registerUser(String userName, String orgName) {
        try {
            String userId = userMgt.addUser(userName);
            addUserToOrg(userId, orgName);
        } catch (Exception e) {
            System.out.println("用户"+ userName + "注册失败！：" + e.getMessage());
            e.printStackTrace();
            return;
        }
        System.out.println("用户" + userName + "注册成功。");
    }

    public void addUserToOrg(String userId, String orgName) {
        System.out.println("userId: " + userId + ", orgName: " + orgName);
    }
}
