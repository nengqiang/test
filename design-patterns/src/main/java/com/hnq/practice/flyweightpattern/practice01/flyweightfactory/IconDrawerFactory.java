package com.hnq.practice.flyweightpattern.practice01.flyweightfactory;

import com.google.common.collect.Maps;
import com.hnq.practice.flyweightpattern.practice01.client.MapDrawer;
import com.hnq.practice.flyweightpattern.practice01.concreteflyweight.IconDrawer;
import com.hnq.practice.flyweightpattern.practice01.flyweight.IIconDrawer;
import com.hnq.practice.flyweightpattern.practice01.unsharedconcreteflyweight.CustomIconDrawer;

import java.util.Map;

/**
 * IconDrawerFactory 是图标绘制器工厂，用于生产图标绘制器。对应于享元模式的参与者，IconDrawerFactory 是享元工厂 FlyweightFactory。
 *
 * @author henengqiang
 * @date 2019/04/18
 */
public class IconDrawerFactory {

    /**
     * 实例
     */
    private static IconDrawerFactory factoryInstance = new IconDrawerFactory();

    public static IconDrawerFactory getInstance() {
        return factoryInstance;
    }

    private IconDrawerFactory() {
    }

    private Map<String, IIconDrawer> iconDrawerMap = Maps.newHashMap();

    public IIconDrawer getIconDrawer(String type) {
        IIconDrawer iconDrawer;
        if (MapDrawer.CUSTOM.equals(type)) {
            iconDrawer = new CustomIconDrawer();
        } else {
            if (iconDrawerMap.containsKey(type)) {
                iconDrawer = iconDrawerMap.get(type);
            } else {
                iconDrawer = new IconDrawer(type);
                iconDrawerMap.put(type, iconDrawer);
            }
        }
        return iconDrawer;
    }
}
