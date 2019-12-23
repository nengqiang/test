package com.hnq.practice.templatepattern.practice01.abstractclass;

/**
 * BaseStorageMgt是库存管理抽象类，实现了模版方法transfer，声明了需要子类实现的子方法add和remove。
 * 对应于模板方法模式的参与者，BaseStorageMgt是抽象类AbstractClass。
 *
 * @author henengqiang
 * @date 2019/08/07
 */
public abstract class BaseStorageMgt {

    /**
     * 入库
     * @param storage
     * @param product
     * @param count
     * @return
     */
    public abstract boolean add(String storage, String product, int count);

    /**
     * 出库
     * @param storage
     * @param product
     * @param count
     * @return
     */
    public abstract boolean remove(String storage, String product, int count);

    /**
     * 移库
     * @param srcStorage
     * @param destStorage
     * @param product
     * @param count
     * @return
     */
    public boolean transfer(String srcStorage, String destStorage, String product, int count) {
        if (remove(srcStorage, product, count)) {
            return add(destStorage, product, count);
        }
        return false;
    }

}
