package com.hnq.study.zklock.service;

import com.hnq.study.zklock.lock.ZkDistributedLock;
import com.hnq.study.zklock.lock.ZkLock;
import com.hnq.study.zklock.utils.OrderNumUtil;

/**
 * @author henengqiang
 * @date 2019/12/17
 */
public class OrderService {

    private ZkLock zkLock = new ZkDistributedLock();

    public void getOrderNum() {
        zkLock.zkLock();
        try {
            System.out.println("生成订单号 " + OrderNumUtil.getOrderNum());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zkLock.zkUnlock();
        }
    }

}
