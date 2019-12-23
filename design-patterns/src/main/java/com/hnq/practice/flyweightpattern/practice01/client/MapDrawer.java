package com.hnq.practice.flyweightpattern.practice01.client;

import com.google.common.collect.Lists;
import com.hnq.practice.flyweightpattern.practice01.flyweight.IIconDrawer;
import com.hnq.practice.flyweightpattern.practice01.flyweightfactory.IconDrawerFactory;
import com.hnq.practice.flyweightpattern.practice01.unsharedconcreteflyweight.CustomIconDrawer;

import java.util.List;

/**
 * MapDrawer 是地图绘制器类，用于绘制地图中的色块、路线、文字及图标。对应于享元模式的参与者，MapDrawer 是客户 Client。
 *
 * @author henengqiang
 * @date 2019/04/18
 */
public class MapDrawer {

    public static final String CUSTOM = "custom";

    private List<Object[]> iconDrawersWithInfo = Lists.newArrayList();

    public void loadIcons(List<Object[]> iconsInfo) {
        IconDrawerFactory iconDrawerFactory = IconDrawerFactory.getInstance();
        iconDrawersWithInfo.clear();
        for (Object[] iconInfo : iconsInfo) {
            Object[] iconDrawerWithInfo = new Object[4];
            iconDrawerWithInfo[0] = iconDrawerFactory.getIconDrawer((String) iconInfo[0]);
            iconDrawerWithInfo[1] = iconInfo[1];
            iconDrawerWithInfo[2] = iconInfo[2];
            iconDrawerWithInfo[3] = iconInfo[3];
            if (CUSTOM.equals(iconInfo[0])) {
                ((CustomIconDrawer) iconDrawerWithInfo[0]).setX((int) iconInfo[1]);
                ((CustomIconDrawer) iconDrawerWithInfo[0]).setY((int) iconInfo[2]);
                ((CustomIconDrawer) iconDrawerWithInfo[0]).setName((String) iconInfo[3]);
                ((CustomIconDrawer) iconDrawerWithInfo[0]).setIconUrl((String) iconInfo[4]);
            }
            iconDrawersWithInfo.add(iconDrawerWithInfo);
        }
    }

    public void draw() {
        System.out.println("清空画布");
        for (Object[] iconDrawerWithInfo : iconDrawersWithInfo) {
            IIconDrawer iconDrawer = (IIconDrawer) iconDrawerWithInfo[0];
            iconDrawer.draw((int) iconDrawerWithInfo[1], (int) iconDrawerWithInfo[2], (String) iconDrawerWithInfo[3]);
        }
    }

}
