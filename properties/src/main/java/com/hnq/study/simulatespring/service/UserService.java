package com.hnq.study.simulatespring.service;

import com.hnq.study.simulatespring.dao.IUserDao;
import com.hnq.study.simulatespring.dao.impl.UserDaoImpl;
import com.hnq.study.simulatespring.model.User;
import lombok.Getter;
import lombok.Setter;

/**
 * @author henengqiang
 * @date 2019/06/18
 */
public class UserService {

    @Getter
    @Setter
    private IUserDao userDao = new UserDaoImpl();

    public void add(User u) {
        this.userDao.save(u);
    }

}
