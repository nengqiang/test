package com.hnq.study.zklock.lock;

import org.I0Itec.zkclient.IZkDataListener;

/**
 * @author henengqiang
 * @date 2019/12/18
 */
public class ZkDistributedLock extends AbstractZkLock {

    @Override
    protected boolean tryZkLock() {
        try {
            zkClient.createEphemeral(path);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void waitZkLock() {
        IZkDataListener listener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                latch.countDown();
            }
        };
        zkClient.subscribeDataChanges(path, listener);
        if (zkClient.exists(path)) {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        zkClient.unsubscribeDataChanges(path, listener);
    }

}
