package com.hnq.practice.adapterpattern.practice01;

import com.hnq.practice.adapterpattern.practice01.adapter.UserOperationAdapter;
import com.hnq.practice.adapterpattern.practice01.client.UserRegister;
import com.hnq.practice.adapterpattern.practice01.targetinterface.IUserMgt;

/**
 * 基于类
 * 场景介绍:
 * 某OA系统可与不同的HR系统集成，用户信息由HR系统维护，用户的组织结构关系由OA系统维护。
 *
 * @author henengqiang
 * @date 2019/03/27
 */
public class Application {

    public static void main(String[] args) {
        testAdapter();
    }

    private static void testAdapter() {
        // 用户管理对象
        IUserMgt userMgt = new UserOperationAdapter();
        // 用户注册对象
        UserRegister userRegister = new UserRegister(userMgt);
        userRegister.registerUser("张三", "销售部");
        System.out.println("----------------------------");
        userRegister.registerUser("李四", "采购部");
    }
}
