package com.hnq.study.zklock.lock;

import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

/**
 * 模板设计模式
 *
 * @author henengqiang
 * @date 2019/12/18
 */
public abstract class AbstractZkLock implements ZkLock {

    private static final String ZK_SERVER = "127.0.0.1:2181";

    private static final int TIME_OUT = 45 * 1000;

    protected ZkClient zkClient = new ZkClient(ZK_SERVER, TIME_OUT);

    protected String path = "/zklock0401";

    protected CountDownLatch latch = new CountDownLatch(1);

    @Override
    public void zkLock() {
        if (tryZkLock()) {
            System.out.println(Thread.currentThread().getName() + "占用锁成功");
        } else {
           waitZkLock();
           zkLock();
        }
    }

    @Override
    public void zkUnlock() {
        if (zkClient != null) {
            zkClient.close();
        }
        System.out.println(Thread.currentThread().getName() + "释放锁成功");
        System.out.println();
        System.out.println();
    }

    protected abstract boolean tryZkLock();

    protected abstract void waitZkLock();

}
