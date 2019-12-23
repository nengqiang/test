package com.hnq.practice.commandpattern.practice01.receiver;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * UserDao 是用户数据访问对象类，提供对数据库的增、删、改、查操作。对应于命令模式的参与者，UserDao 是命令接收者 Receiver。
 *
 * @author henengqiang
 * @date 2019/04/26
 */
public class UserDao {

    private static Map<String, String> userMap = Maps.newHashMap();

    public void insertUser(String id, String name) {
        System.out.println("访问数据库： insert into t_user (user_id, user_name) values(" + id + "," + name + ")");
        userMap.put(id, name);
    }

    public void delUser(String id) {
        System.out.println("访问数据库： delete from t_user where user_id = " + id);
        userMap.remove(id);
    }

    public void updateUserInfo(String id, String name) {
        System.out.println("访问数据库： update t_user set user_name = " + name + " where user_id = " + id);
        userMap.put(id, name);
    }

    public String getUserName(String id) {
        System.out.println("访问数据库： select user_id, user_name from t_user where user_id = " + id);
        return userMap.get(id);
    }

}
