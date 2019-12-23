package com.hnq.study.zklock.lock;

/**
 * @author henengqiang
 * @date 2019/12/18
 */
public interface ZkLock {

    void zkLock();

    void zkUnlock();

}
