package com.hnq.study.simulatespring.dao;

import com.hnq.study.simulatespring.model.User;

/**
 * @author henengqiang
 * @date 2019/06/18
 */
public interface IUserDao {

    void save(User u);

    void print();

}
