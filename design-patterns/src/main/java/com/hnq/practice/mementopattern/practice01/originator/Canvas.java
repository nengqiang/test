package com.hnq.practice.mementopattern.practice01.originator;

import com.hnq.practice.mementopattern.practice01.memento.Memento;

import java.util.Arrays;

/**
 * Canvas是画布类，实现了向指定坐标绘制像素点的功能。对应于备忘录模式的参与者，Canvas是原发器Originator。
 *
 * @author henengqiang
 * @date 2019/06/21
 */
public class Canvas {

    /**
     * 画布像素矩阵
     */
    private byte[][] pixels = new byte[3][3];

    /**
     * 填充颜色
     * @param x     x坐标
     * @param y     y坐标
     * @param color 颜色（1-255）
     */
    public void fill(int x, int y, int color) {
        pixels[x][y] = (byte) color;
    }

    /**
     * 创建备忘录
     * @return  备忘录
     */
    public Memento createMemento() {
        Memento memento = new Memento();
        memento.setPixels(pixels);
        return memento;
    }

    /**
     * 还原画布
     */
    public void revertCanvas(Memento memento) {
        byte[][] pixels = memento.getPixels();
        for (int i = 0; i < pixels.length; i++) {
            this.pixels[i] = Arrays.copyOf(pixels[i], pixels[i].length);
        }
    }

    /**
     * 输出像素矩阵
     */
    public void outputPixels() {
        for (byte[] pixel : pixels) {
            StringBuilder sb = new StringBuilder();
            for (byte b : pixel) {
                sb.append(String.format("%1$-4s", decodeColor(b)));
            }
            System.out.println(sb.toString());
        }
    }

    /**
     * 解析颜色值
     */
    private int decodeColor(byte colorByte) {
        return colorByte < 0 ? 256 + colorByte : colorByte;
    }

}
