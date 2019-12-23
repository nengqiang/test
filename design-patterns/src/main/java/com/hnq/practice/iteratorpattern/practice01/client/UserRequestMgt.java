package com.hnq.practice.iteratorpattern.practice01.client;

import com.hnq.practice.iteratorpattern.practice01.concreteaggregate.UserRequestList;
import com.hnq.practice.iteratorpattern.practice01.model.UserRequest;

import java.util.Iterator;

/**
 * UserRequestMgt 是用户请求管理类，用于管理用户请求。对应于迭代器模式的参与者，UserRequestMgt 是客户 Client。
 *
 * @author henengqiang
 * @date 2019/05/31
 */
public class UserRequestMgt {

    /**
     * 用户请求集合
     */
    private UserRequestList userRequestList;

    /**
     * 用户请求迭代器
     */
    private Iterator userRequestIterator;

    public UserRequestMgt() {
        this.userRequestList = new UserRequestList();
        this.userRequestIterator = userRequestList.iterator();
    }

    public UserRequest getRequest() {
        UserRequest userRequest = null;
        if (userRequestIterator.hasNext()) {
            userRequest = (UserRequest) userRequestIterator.next();
            userRequestIterator.remove();
        }
        return userRequest;
    }

    public void putRequest(UserRequest userRequest) {
        userRequestList.add(userRequest);
    }
}
