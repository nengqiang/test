package com.hnq.practice.mementopattern.practice01.memento;

import lombok.Getter;

import java.util.Arrays;
import java.util.Date;

/**
 * Memento是备忘录类，维护某一时刻画布的像素矩阵。对应于备忘录模式的参与者，Memento即是备忘录Memento。
 *
 * @author henengqiang
 * @date 2019/06/21
 */
@Getter
public class Memento {

    /**
     * 画布像素矩阵
     */
    private byte[][] pixels = new byte[3][3];

    /**
     * 记录时间
     */
    private Date recordDate;

    public void setPixels(byte[][] pixels) {
        for (int i = 0; i < pixels.length; i++) {
            this.pixels[i] = Arrays.copyOf(pixels[i], pixels[i].length);
        }
        this.recordDate = new Date();
    }

}
