package com.hnq.practice.facadepattern.practice01.facade;

import com.hnq.practice.facadepattern.practice01.subsystem.OrgMgt;
import com.hnq.practice.facadepattern.practice01.subsystem.UserMgt;

/**
 * UserMgtService，即用户管理服务，是HR系统对外部系统开放的API入口。对应于外观模式的参与者，UserMgtService是外观Facade。
 *
 * @author henengqiang
 * @date 2019/04/16
 */
public class UserMgtService {

    public void addUser(String id, String name) {
        UserMgt userMgt = new UserMgt();
        userMgt.add(id, name);
    }

    public void addOrg(String id, String name) {
        OrgMgt orgMgt = new OrgMgt();
        orgMgt.add(id, name);
    }

}
