package com.hnq.practice.abstractfactory.practice01;

import com.hnq.practice.abstractfactory.practice01.client.UserInfoViewer;

/**
 * @author henengqiang
 * @date 2019/03/07
 */
public class Application {

    public static void main(String[] args) {
        userInfoTest();
    }

    private static void userInfoTest() {
        UserInfoViewer userInfoViewer = new UserInfoViewer();
        userInfoViewer.outputUserInfo(UserInfoViewer.userId1);
        System.out.println();
        userInfoViewer.outputUserInfo(UserInfoViewer.userId2);
    }

}
