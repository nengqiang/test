package com.hnq.practice.factorypattern.practice01;

import com.hnq.practice.factorypattern.practice01.client.UserInfoViewer;

import static com.hnq.practice.factorypattern.practice01.client.UserInfoViewer.userId1;
import static com.hnq.practice.factorypattern.practice01.client.UserInfoViewer.userId2;

/**
 * 场景介绍:
 * 某电商平台将用户分为普通用户和VIP用户，不同种类的用户享受的折扣率不同，显示的用户信息栏目也不同。用户信息查看器用于输出用户的信息。
 *
 * @author henengqiang
 * @date 2019/03/11
 */
public class Application {

    public static void main(String[] args) {
        factoryTest();
    }

    private static void factoryTest() {
        UserInfoViewer userInfoViewer = new UserInfoViewer();
        userInfoViewer.viewUserInfo(userId1);
        userInfoViewer.viewUserInfo(userId2);
    }

}
