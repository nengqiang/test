package com.hnq.practice.singleresponsibility.practice01;

/**
 * @author henengqiang
 * @date 2019/08/20
 */
public class Demo03 {

    public static void main(String[] args) {
        Vehicle2 v = new Vehicle2();
        v.runRoad("摩托车");
        v.runAir("飞机");
        v.runWater("潜水艇");
    }

}

class Vehicle2 {
    public void runRoad(String vehicle) {
        System.out.println(vehicle + "在公路上运行");
    }

    public void runAir(String vehicle) {
        System.out.println(vehicle + "在天上运行");
    }

    public void runWater(String vehicle) {
        System.out.println(vehicle + "在水里运行");
    }
}
