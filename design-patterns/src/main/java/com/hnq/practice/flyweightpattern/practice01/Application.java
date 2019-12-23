package com.hnq.practice.flyweightpattern.practice01;

import com.google.common.collect.Lists;
import com.hnq.practice.flyweightpattern.practice01.client.MapDrawer;

import java.util.List;

/**
 * Flyweight:
 *  享元接口，声明了享元提供的各接口方法
 *
 * ConcreteFlyweight:
 *  具体享元类，实现了享元Flyweight接口。ConcreteFlyweight的状态分为内、外两部分。内部状态由ConcreteFlyweight维护，在客户间共享；
 * 外部状态不在其维护范围内，由客户调用其接口方法时传入。ConcreteFlyweight对象是真正在客户间共享的对象。
 *
 * UnsharedConcreteFlyweight:
 *  非共享的具体享元类，实现了享元Flyweight接口。并不是所有实现了享元接口的类对象都必需要共享，UnsharedConcreteFlyweight类对象
 * 就不会被共享。另外，与ConcreteFlyweight相比，UnsharedConcreteFlyweight还维护了全部状态。
 *
 * FlyweightFactory:
 *  FlyweightFactory是享元工厂，用于制造享元对象。FlyweightFactory内部维护了一个享元池，当要获取的享元对象已存在时从享元池中直接
 * 返回该对象，否则创建该对象返回，并将其加入享元池。
 *
 * Client:
 *  客户，是享元模式的使用者。它通过享元工厂生产享元对象，维护享元对象的外部状态，并通过传入这些外部状态调用享元的具体方法。
 *
 * 场景介绍：
 *  某地图应用绘制的地图包含各种图标，这些图标中既有指定类型的图标又有自定义图标。其中，指定类型图标根据类型显示相应的图标，
 * 自定义类型图标根据指定图标文件url显示图标。
 *
 * @author henengqiang
 * @date 2019/04/18
 */
public class Application {

    public static void main(String[] args) {
        flyweightTest();
    }

    private static void flyweightTest() {
        List<Object[]> iconsInfo = Lists.newArrayList();

        // 建筑图标1（将创建新享元对象）
        Object[] buildingIconInfo1 = new Object[5];
        buildingIconInfo1[0] = "building";
        buildingIconInfo1[1] = 23;
        buildingIconInfo1[2] = 45;
        buildingIconInfo1[3] = "世界大厦";
        iconsInfo.add(buildingIconInfo1);

        // 医院图标（将创建新享元对象）
        Object[] hospitalIconInfo = new Object[5];
        hospitalIconInfo[0] = "hospital";
        hospitalIconInfo[1] = 451;
        hospitalIconInfo[2] = 33;
        hospitalIconInfo[3] = "海淀医院";
        iconsInfo.add(hospitalIconInfo);

        // 家的位置（将创建新的享元对象）
        Object[] homeIconInfo = new Object[5];
        homeIconInfo[0] = "custom";
        homeIconInfo[1] = 525;
        homeIconInfo[2] = 124;
        homeIconInfo[3] = "月泉路";
        homeIconInfo[4] = "http://localhost:8888/icons/house.ico";
        iconsInfo.add(homeIconInfo);

        // 建筑图标2（重用享元对象）
        Object[] buildingIconInfo2 = new Object[5];
        buildingIconInfo2[0] = "building";
        buildingIconInfo2[1] = 23;
        buildingIconInfo2[2] = 45;
        buildingIconInfo2[3] = "海龙大厦";
        iconsInfo.add(buildingIconInfo2);

        // 公司的位置（将创建新的享元对象）
        Object[] companyIconInfo = new Object[5];
        companyIconInfo[0] = "custom";
        companyIconInfo[1] = 352;
        companyIconInfo[2] = 85;
        companyIconInfo[3] = "清华东路";
        companyIconInfo[4] = "http://12.125.23.64/icons/company.ico";
        iconsInfo.add(companyIconInfo);

        MapDrawer leftMapDrawer = new MapDrawer();
        leftMapDrawer.loadIcons(iconsInfo);

        leftMapDrawer.draw();
        System.out.println("--------------------------------------------------------");

        leftMapDrawer.draw();
        System.out.println("--------------------------------------------------------");

        MapDrawer rightMapDrawer = new MapDrawer();
        rightMapDrawer.loadIcons(iconsInfo);
        rightMapDrawer.draw();
    }

}
