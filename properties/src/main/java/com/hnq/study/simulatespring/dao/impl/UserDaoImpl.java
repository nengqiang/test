package com.hnq.study.simulatespring.dao.impl;

import com.hnq.study.simulatespring.dao.IUserDao;
import com.hnq.study.simulatespring.model.User;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author henengqiang
 * @date 2019/06/18
 */
@Data
public class UserDaoImpl implements IUserDao {

    private Set<String> sets;

    private List<String> lists;

    private Map<String, String> maps;

    @Override
    public void save(User u) {
        System.out.println("Add Success!");
    }

    @Override
    public void print() {
        System.out.println(this.toString());
    }
}
