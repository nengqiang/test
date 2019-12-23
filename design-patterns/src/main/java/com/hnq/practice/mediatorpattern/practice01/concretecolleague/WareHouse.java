package com.hnq.practice.mediatorpattern.practice01.concretecolleague;

import com.google.common.collect.Maps;
import com.hnq.practice.mediatorpattern.practice01.collegue.BaseInvChgColleague;
import com.hnq.practice.mediatorpattern.practice01.mediator.BaseInvChgMediator;

import java.util.Map;

/**
 * Warehouse 是仓库类，派生于库存改变同事类 BaseInvChgColleague。
 * Warehouse 重写了其感兴趣的通知处理方法并声明了自己的业务方法。
 * 对应于中介者模式的参与者，Warehouse 是具体同事类 ConcreteColleague。
 *
 * @author henengqiang
 * @date 2019/06/17
 */
public class WareHouse extends BaseInvChgColleague {

    /**
     * 库存字典
     */
    private Map<String, Integer> inventoryMap = Maps.newHashMap();

    /**
     * 仓库名
     */
    private String name;

    public WareHouse(String name, BaseInvChgMediator invChgMediator) {
        super(invChgMediator);
        this.name = name;
    }

    @Override
    public int getTotal(String productName) {
        return inventoryMap.getOrDefault(productName, 0);
    }

    @Override
    public int productConsumed(String productName, int count) {
        // 处理的商品数量
        int handledProductCount = 0;
        // 库存中存在的商品
        if (inventoryMap.containsKey(productName)) {
            if (inventoryMap.get(productName) < count) {
                handledProductCount = inventoryMap.get(productName);
            } else {
                handledProductCount = count;
            }
            remove(productName, handledProductCount);
        }
        return handledProductCount;
    }

    /**
     * 入库
     */
    public void add(String productName, int count) {
        int originalCount = inventoryMap.getOrDefault(productName, 0);
        inventoryMap.put(productName, originalCount + count);
        invChgMediator.inventoryChg(productName);
    }

    /**
     * 出库
     */
    public void remove(String productName, int count) {
        if (inventoryMap.containsKey(productName) && inventoryMap.get(productName) >= count) {
            inventoryMap.put(productName, inventoryMap.get(productName) - count);
            invChgMediator.inventoryChg(productName);
        } else {
            System.out.println("库存不足，出库失败");
        }
    }

    /**
     * 打印明细
     */
    public void printDetail() {
        System.out.println("仓库" + name + "明细：");
        inventoryMap.entrySet().forEach(System.out::println);
        System.out.println("--------------------------");
    }
}
