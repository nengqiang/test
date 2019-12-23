package com.hnq.practice.adapterpattern.practice01.targetinterface;

/**
 * 目标接口
 *
 * @author henengqiang
 * @date 2019/03/27
 */
public interface IUserMgt {

    /**
     * addUser
     *
     * @param name  name
     * @return      新用户id
     * @throws      RuntimeException see {@link RuntimeException}
     */
    String addUser(String name) throws RuntimeException;

}
