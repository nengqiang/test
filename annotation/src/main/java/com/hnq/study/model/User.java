package com.hnq.study.model;

import com.hnq.study.annotation.Init;
import com.hnq.study.annotation.Validate;

/**
 * @author henengqiang
 * @date 2019/03/07
 */
public class User {

    @Validate(min = 2, max = 5)
    private String name;

    @Validate(isNotNull = false)
    private String gender;

    public User() {
    }

    public User(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    @Init(value = "Alice")
    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    @Init(value = "female")
    public void setGender(String gender) {
        this.gender = gender;
    }
}
