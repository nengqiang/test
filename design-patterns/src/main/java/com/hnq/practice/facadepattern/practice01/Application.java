package com.hnq.practice.facadepattern.practice01;

import com.hnq.practice.facadepattern.practice01.facade.UserMgtService;

/**
 * 场景介绍：
 * 某HR系统包含用户管理和组织机构管理的相关功能。为了与第三方系统集成，该系统开放了API，提供添加用户和添加组织机构等接口方法。
 *
 * @author henengqiang
 * @date 2019/04/16
 */
public class Application {

    public static void main(String[] args) {
        facadeTest();
    }

    private static void facadeTest() {
        UserMgtService userMgtService = new UserMgtService();
        userMgtService.addUser("001", "张三");
        userMgtService.addOrg("001", "财务部");
    }

}
