package com.hnq.test.login;

import org.apache.commons.lang3.RandomUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author henengqiang
 * @date 2019/04/15
 */
public class DrawLineTest extends Frame {

    public static void main(String[] args) {
        //在主函数中创建类并调用启动窗口
        new DrawLineTest().launchFrame();
//        new DrawLineTest().orbitSimulation();
    }

    void launchFrame() {
        //设置窗口大小
        setSize(450, 250);
        //setLocation()是JFrame里面的方法，设置控件在窗体里初始化的位置
        setLocation(150, 150);
        //设置窗口背景颜色
//		setBackground(Color.blue);
        //设置窗口标题
//        setTitle("");
        // 去掉标题栏
        dispose();
        setUndecorated(true);
        //设置是否显示
        setVisible(true);

    }

    public void paint(Graphics g) {
//        g.drawLine(100, 100, 200, 200); //使用g.drawLine 在窗口里面划线
//        g.drawRect(100, 100, 100, 100);	//使用g.drawRect 在窗口里面画矩形
//        g.drawOval(100, 100, 100, 100);	//使用g.drawOval 在窗口里面画圆形
//        g.drawOval(100, 100, 200, 100); //使用g.drawOval 在窗口里面画圆形 圆的长度和宽度不一样就是椭圆
//        Font f=new Font("宋体",Font.BOLD,25 ); //使用font设置
//        Color c=g.getColor(); //获取画笔的初始颜色
//        g.setFont(f); //使用 setfont 调用字体
//        g.setColor(Color.red);
//        g.drawString("开始游戏", 120, 150); //使用g.drawString 在窗口显示字符串
//        g.setColor(c);	//画笔使用完成之后 在编程出事的颜色
//        g.fillRect(0, 0, 100, 100); //使用g.fillRect 填充窗口矩形填充
//        g.setColor(Color.red);	//使用g.setColor 给画笔改变颜色
//        g.fillOval(200, 200, 50, 50);	//使用g.fillRect 填充窗口 圆形填充
//        g.setColor(c);	//画笔使用完成之后 在编程出事的颜色
//        g.drawImage(img, 150, 150, null);

        // -------
//        orbitSimulation(g);
        orbitSimulation1(g);
        orbitSimulation2(g);
    }

    private void orbitSimulation(Graphics g) {
//        int x1 = 100;
//        int y1 = 0;
//        int x2 = 100;
//        int y2 = 200;

        Random r = new Random();
        int x1 = r.nextInt(450);
        int y1 = r.nextInt(250);
        System.out.println("point1(" + x1 + ", " + y1 + ")");
        int x2 = r.nextInt(450);
        int y2 = r.nextInt(250);
        System.out.println("point2(" + x2 + ", " + y2 + ")");
        int sx = Math.abs(x1 - x2);
        int sy = Math.abs(y1 - y2);
        boolean xShorter = sx < sy;
        int stepNum = xShorter ? sx : sy;
        if (stepNum == 0) {
            stepNum = 200;
        }
        int pointX = x1, pointY = y1;

        int boundX = xShorter ? 3 : 6;
        int boundY = xShorter ? 6 : 3;
        for (int i = 0; i < stepNum / 2; i++) {
            int lastPointX = pointX;
            int lastPointY = pointY;
            if (x1 != x2 && y1 != y2) {
                pointX += x2 > x1 ? Math.abs(x1 - x2) / stepNum + r.nextInt(boundX) : -Math.abs(x1 - x2) / stepNum - r.nextInt(boundX);
                pointY += y2 > y1 ? Math.abs(y1 - y2) / stepNum + r.nextInt(boundY) : -Math.abs(y1 - y2) / stepNum - r.nextInt(boundY);
            } else if (x1 == x2 && y1 != y2) {
                pointX += getRandomNum(2);
                pointY += y2 > y1 ? Math.abs(y1 - y2) / stepNum + r.nextInt(boundY) : -Math.abs(y1 - y2) / stepNum - r.nextInt(boundY);
            } else if (x1 != x2 && y1 == y2) {
                pointX += x2 > x1 ? Math.abs(x1 - x2) / stepNum + r.nextInt(boundX) : -Math.abs(x1 - x2) / stepNum - r.nextInt(boundX);
                pointY += getRandomNum(2);
            }
            pointX = pointX <= 0 ? 2 : pointX;
            pointY = pointY <= 0 ? 2 : pointY;
            g.drawLine(lastPointX, lastPointY, pointX, pointY);
            System.out.println("x=" + pointX + ", y=" + pointY);
        }
    }

    private void orbitSimulation1(Graphics g) {
        Random r = new Random();
        int x1 = r.nextInt(450);
        int y1 = r.nextInt(250);
        System.out.println("point1(" + x1 + ", " + y1 + ")");
        int x2 = r.nextInt(450);
        int y2 = r.nextInt(250);
        System.out.println("point2(" + x2 + ", " + y2 + ")");
//        int x1 = 100;
//        int y1 = 50;
//        int x2 = 100;
//        int y2 = 250;

        int sx = Math.abs(x1 - x2);
        int sy = Math.abs(y1 - y2);
        int stepNum = RandomUtils.nextInt(30, 60 + 1);
        System.out.println("stepNum=" + stepNum);
        int[] orbitX = randomSplit(sx, stepNum, sx / stepNum - 3, sx / stepNum + 3);
        int[] orbitY = randomSplit(sy, stepNum, sy / stepNum - 3, sy / stepNum + 3);

        int pointX = x1, pointY = y1;
        for (int i = 0; i < stepNum; i++) {
            int lastPointX = pointX;
            int lastPointY = pointY;
            pointX = x2 > x1 ? pointX + orbitX[i] : pointX - orbitX[i];
            pointY = y2 > y1 ? pointY + orbitY[i] : pointY - orbitY[i];
            pointX = pointX < 0 ? lastPointX : pointX;
            pointY = pointY < 0 ? lastPointY : pointY;
            g.drawLine(lastPointX, lastPointY, pointX, pointY);
            System.out.println("(" + pointX + ", " + pointY + ")");
        }
    }

    private static java.util.List<Integer> arraysX = new ArrayList<>();

    private static List<Integer> arraysY = new ArrayList<>();

    private void orbitSimulation2(Graphics g) {
        Random r = new Random();
        int x1 = r.nextInt(450);
        int y1 = r.nextInt(250);
        System.out.println("point1(" + x1 + ", " + y1 + ")");
        int x2 = r.nextInt(450);
        int y2 = r.nextInt(250);
        System.out.println("point2(" + x2 + ", " + y2 + ")");
//        int x1 = 100;
//        int y1 = 50;
//        int x2 = 100;
//        int y2 = 250;

        int flagX = x2 > x1 ? 1 : -1;
        int flagY = y2 > y1 ? 1 : -1;
        arraysX = new ArrayList<>();
        getRandomArrayX(Math.abs(x2 - x1));

        arraysY = new ArrayList<>();
        getRandomArrayY(Math.abs(y2 - y1), arraysX.size());

        int pointX = x1, pointY = y1;
        for (int i = 0; i < arraysX.size(); i++) {
            int offsetX = arraysX.get(i);
            int offsetY = arraysY.get(i);
            int lastPointX = pointX;
            int lastPointY = pointY;
            pointX += offsetX * flagX;
            pointY += offsetY * flagY;
            g.setColor(Color.red);
            g.drawLine(lastPointX, lastPointY, pointX, pointY);
            System.err.println("(" + pointX + ", " + pointY + ")");
        }

    }

    private void getRandomArrayX(int sum) {
        int i = RandomUtils.nextInt(1, 10);
        int j = sum - i;
        if (j <= 0) {
            arraysX.add(sum);
        } else {
            arraysX.add(i);
            getRandomArrayX(j);
        }
    }

    private void getRandomArrayY(int sum, int n) {
        int i = RandomUtils.nextInt(sum / n / 2, sum / n * 2);
        int j = sum - i;
        if (j <= 0) {
            arraysY.add(sum);
        } else {
            arraysY.add(i);
            if (n > 1) {
                getRandomArrayY(j, n - 1);
            }
        }

    }

    private int[] randomSplit(int x, int n, int min, int max) {
        int[] result = new int[n];
        min = min < 0 ? 0 : min;
        int i = 0;
        while (n > 0) {
            int l = Math.max(min, x - (n - 1) * max);
            int r = Math.min(max, x - (n - 1) * min);
            int num = RandomUtils.nextInt(l, r + 1);
            n -= 1;
            x -= num;
            result[i++] = num;
        }
        return result;
    }

    private int getRandomNum(int bound) {
        Random r = new Random();
        return bound - r.nextInt(bound * 2 + 1);
    }

}
