package com.hnq.practice.mementopattern.practice01;

import com.hnq.practice.mementopattern.practice01.caretaker.Drawer;

/**
 * 1 Memento
 *  Memento是备忘录，用于保存对象某一时刻的内部状态。这些被保存的内部状态应该被隐藏，仅对创建它的原发器Originator可见。
 * 2 Originator
 *  Originator是原发器，负责将对象内部状态保存为备忘录以及根据备忘录还原对象的内部状态。
 * 3 Caretaker
 *  Caretaker是管理者，负责触发保存和还原对象事件以及对备忘录的保存。
 *
 * 场景介绍：
 *  某平面绘制工具可以在3*3的画布上用255种颜色的画笔绘制图形。画布不支持图层，后绘制的图形会覆盖先绘制的图形。
 * 该工具提供撤销操作，可以撤销最后一步操作。
 *
 * @author henengqiang
 * @date 2019/06/21
 */
public class Application {

    public static void main(String[] args) {
        mementoTest();
    }

    private static void mementoTest() {
        Drawer drawer = new Drawer();
        drawer.drawLine(0, 0, 2, 0, 1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        drawer.drawLine(0, 0, 0, 2, 255);
        drawer.withDraw();
        drawer.withDraw();
    }

}
