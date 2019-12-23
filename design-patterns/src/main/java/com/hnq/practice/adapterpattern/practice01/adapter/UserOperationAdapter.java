package com.hnq.practice.adapterpattern.practice01.adapter;

import com.hnq.practice.adapterpattern.practice01.originalinterface.UserOperation;
import com.hnq.practice.adapterpattern.practice01.targetinterface.IUserMgt;

/**
 * @author henengqiang
 * @date 2019/03/27
 */
public class UserOperationAdapter extends UserOperation implements IUserMgt {

    @Override
    public String addUser(String name) throws RuntimeException {
        int userSn = getUserSn(name);
        if (userSn > 0) {
            throw new RuntimeException("用户已存在，添加用户失败");
        }
        if (!insertUser(name)) {
            throw  new RuntimeException("未知错误，添加用户失败");
        }
        userSn = getUserSn(name);
        return String.valueOf(userSn);
    }

}
