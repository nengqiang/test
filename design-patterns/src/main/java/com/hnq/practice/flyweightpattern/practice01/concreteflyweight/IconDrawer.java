package com.hnq.practice.flyweightpattern.practice01.concreteflyweight;

import com.hnq.practice.flyweightpattern.practice01.flyweight.IIconDrawer;

/**
 * IconDrawer 是具体图标绘制器类，实现了 IIconDrawer 接口，用于绘制图标。对应于享元模式的参与者，
 * IconDrawer 是具体享元类 ConcreteFlyweight。
 *
 * @author henengqiang
 * @date 2019/04/18
 */
public class IconDrawer implements IIconDrawer {

    private String type;

    public IconDrawer(String type) {
        this.type = type;
    }

    @Override
    public void draw(int x, int y, String name) {
        System.out.println(String.format("在坐标(%d, %d)绘制了名为【%s】的【%s】类型图标", x, y, name, type));
    }
}
