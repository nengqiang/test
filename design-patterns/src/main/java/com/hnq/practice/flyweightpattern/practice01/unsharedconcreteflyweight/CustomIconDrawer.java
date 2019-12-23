package com.hnq.practice.flyweightpattern.practice01.unsharedconcreteflyweight;

import com.hnq.practice.flyweightpattern.practice01.flyweight.IIconDrawer;

/**
 * CustomIconDrawer 是自定义图标绘制器类，用于绘制自定义图标。它虽然也实现了 IIconDrawer 接口，但它并不用于共享。
 * 对应于享元模式的参与者，CustomIconDrawer 是非共享具体享元 UnsharedConcreteFlyweight。
 *
 * @author henengqiang
 * @date 2019/04/18
 */
public class CustomIconDrawer implements IIconDrawer {

    private String iconUrl;

    private int x;

    private int y;

    private String name;

    @Override
    public void draw(int x, int y, String name) {
        System.out.println(String.format("在坐标(%d, %d)绘制了名为【%s】的自定义图标，url=%s", x, y, name, iconUrl));
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
