package com.hnq.practice.iteratorpattern.practice01.concreteiterator;

import com.google.common.collect.Lists;
import com.hnq.practice.iteratorpattern.practice01.model.UserRequest;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * UserRequestIterator 是用户请求迭代器，用于遍历 UserRequestList 维护的 UserRequest 对象集合。
 * UserRequestIterator 实现了 java.util.Iterator 接口。对应于迭代器模式的参与者，
 * UserRequestIterator 是具体迭代器类 ConcreteIterator。
 *
 * @author henengqiang
 * @date 2019/05/31
 */
public class UserRequestIterator implements Iterator {

    private List<UserRequest> userRequests;

    private UserRequest lastUserRequest;

    private List<String> doneRequestCodes = Lists.newArrayList();

    public UserRequestIterator(List<UserRequest> userRequests) {
        this.userRequests = userRequests;
    }

    @Override
    public boolean hasNext() {
        return doneRequestCodes.size() < userRequests.size();
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        // 下一个请求
        UserRequest nextUserRequest = null;
        for (UserRequest request : userRequests) {
            // 改请求已遍历
            if (doneRequestCodes.contains(request.getRequestCode())) {
                continue;
            }
            // 还没有可比较请求
            if (nextUserRequest == null) {
                nextUserRequest = request;
                continue;
            }
            if (compareUserRequest(request, nextUserRequest) > 0) {
                nextUserRequest = request;
            }
        }
        lastUserRequest = nextUserRequest;
        doneRequestCodes.add(nextUserRequest != null ? nextUserRequest.getRequestCode() : "-1");
        return nextUserRequest;
    }

    @Override
    public void remove() {
        if (lastUserRequest != null) {
            doneRequestCodes.remove(lastUserRequest.getRequestCode());
            userRequests.remove(lastUserRequest);
            lastUserRequest = null;
        }
    }

    /**
     * 比较用户请求优先级
     * @return 用户请求1优先级高于用于请求2返回1；用户请求1优先级等于用于请求2返回0；用户请求1优先级低于用于请求2返回-1
     */
    private int compareUserRequest(UserRequest userRequest1, UserRequest userRequest2) {
        // VIP 比较
        if (userRequest1.isVip() != userRequest2.isVip()) {
            if (userRequest1.isVip()) {
                return 1;
            } else {
                return -1;
            }
        }
        // 请求类型比较
        if (userRequest1.getType() != userRequest2.getType()) {
            if (userRequest1.getType() < userRequest2.getType()) {
                return 1;
            } else {
                return -1;
            }
        }
        // 日期比较
        return userRequest2.getRequestDate().compareTo(userRequest1.getRequestDate());
    }
}
