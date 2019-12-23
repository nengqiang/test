package com.hnq.practice.mementopattern.practice01.caretaker;

import com.google.common.collect.Lists;
import com.hnq.practice.mementopattern.practice01.memento.Memento;
import com.hnq.practice.mementopattern.practice01.originator.Canvas;
import com.hnq.toolkit.consts.DateConsts;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.List;

/**
 * Drawer是绘制器类，用于实现绘制功能。对应于备忘录模式的参与者，Drawer是管理者Caretaker。
 *
 * @author henengqiang
 * @date 2019/06/21
 */
public class Drawer {

    /**
     * 画布
     */
    private Canvas canvas = new Canvas();

    /**
     * 备忘录集合
     */
    private List<Memento> mementos = Lists.newArrayList();

    /**
     * 绘制线段
     */
    public void drawLine(int fromX, int fromY, int toX, int toY, int color) {
        mementos.add(canvas.createMemento());
        System.out.println("绘制线段(" + fromX + ", " + fromY + "), (" + toX + ", " + toY + "), " + color + ":");
        if (fromX == toX) {
            int minY = Math.min(fromY, toY);
            int maxY = Math.max(fromY, toY);
            for (int i = minY; i < maxY + 1; i++) {
                canvas.fill(fromX, i, color);
            }
        } else if (fromY == toY) {
            int minX = Math.min(fromX, toX);
            int maxX = Math.max(fromX, toX);
            for (int i = minX; i < maxX + 1; i++) {
                canvas.fill(i, fromY, color);
            }
        }
        canvas.outputPixels();
    }

    public void withDraw() {
        if (mementos.size() < 1) {
            return;
        }
        // 上一步备忘录
        Memento memento = mementos.get(mementos.size() - 1);
        System.out.println("撤销最后一步操作（恢复" + DateFormatUtils.format(memento.getRecordDate(), DateConsts.DEFAULT_PATTERN) + "时的画布）：");
        canvas.revertCanvas(memento);
        canvas.outputPixels();
        mementos.remove(memento);
    }

}
