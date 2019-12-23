package com.hnq.practice.flyweightpattern.practice01.flyweight;

/**
 * IconDrawer 是图标绘制器接口。它定义了绘制图标接口方法 draw。对应于享元模式的参与者，IIconDrawer 是享元 Flyweight。
 *
 * @author henengqiang
 * @date 2019/04/18
 */
public interface IIconDrawer {

    /**
     * 绘制图标
     * @param x     横坐标
     * @param y     纵坐标
     * @param name  显示名称
     */
    void draw(int x, int y, String name);

}
