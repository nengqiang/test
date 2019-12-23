package com.hnq.practice.singleresponsibility.practice01;

/**
 * @author henengqiang
 * @date 2019/08/20
 */
public class Demo02 {

    public static void main(String[] args) {

    }

}

/*
 * 遵循单一职责原则，但是改动很大
 */

class RoadVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + "在公路上运行");
    }
}

class AirVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + "在天上运行");
    }
}

class WaterVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + "在水里运行");
    }
}
