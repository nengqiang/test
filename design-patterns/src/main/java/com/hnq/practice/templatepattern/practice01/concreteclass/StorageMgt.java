package com.hnq.practice.templatepattern.practice01.concreteclass;

import com.hnq.practice.templatepattern.practice01.abstractclass.BaseStorageMgt;

/**
 * StorageMgt是库存管理类，派生于抽象类BaseStorageMgt。对应于模板方法模式的参与者，StorageMgt是具体类ConcreteClass。
 *
 * @author henengqiang
 * @date 2019/08/07
 */
public class StorageMgt extends BaseStorageMgt {

    @Override
    public boolean add(String storage, String product, int count) {
        System.out.println("添加库存 storage=" + storage + ", product=" + product + ", count=" + count);
        System.out.println("更新库存");
        return true;
    }

    @Override
    public boolean remove(String storage, String product, int count) {
        if (count > getProductCount(storage, product)) {
            return false;
        }
        System.out.println("减少库存 storage=" + storage + ", product=" + product + ", count=" + count);
        System.out.println("更新库存");
        return true;
    }

    private int getProductCount(String storage, String product) {
        System.out.println("查询库存 storage=" + storage + ", product=" + product);
        return 100;
    }

}
