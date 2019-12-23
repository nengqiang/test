package com.hnq.practice.iteratorpattern.practice01.concreteaggregate;

import com.google.common.collect.Lists;
import com.hnq.practice.iteratorpattern.practice01.concreteiterator.UserRequestIterator;
import com.hnq.practice.iteratorpattern.practice01.model.UserRequest;

import java.util.Iterator;
import java.util.List;

/**
 * UserRequestList 是用户请求集合，提供了对 UserRequest 类型对象集合的维护。UserRequestList 实现了 java.lang.Iterable 接口。
 * 对应于迭代器模式的参与者，UserRequestList 是具体集合类 ConcreteAggregate。
 *
 * @author henengqiang
 * @date 2019/05/31
 */
public class UserRequestList implements Iterable {

    private List<UserRequest> userRequests = Lists.newArrayList();

    @Override
    public Iterator iterator() {
        return new UserRequestIterator(userRequests);
    }

    public void add(UserRequest userRequest) {
        userRequests.add(userRequest);
    }
}
