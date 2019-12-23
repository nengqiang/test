package com.hnq.study.tenquestions;

/**
 * 啤酒与饮料
 *
 * 啤酒每罐2.3元，饮料每罐1.9元。小明买了若干啤酒和饮料，一共花了82.3元。我们还知道他买的啤酒比饮料的数量少，请你计算他买了几罐啤酒。
 *
 * @author henengqiang
 * @date 2018-12-12
 */
public class BearAndDrink {

    public static void main(String[] args) {
        methodA();
    }

    private static void methodA() {
        int beerNumMax = (int) (82.3 / 2.3);
        int drinkNumMax = (int) (82.3 / 1.9);
        // 啤酒
        for (int i = 0; i < beerNumMax; i++) {
            // 饮料
            for (int j = 0; j < drinkNumMax; j++) {
                if (i * 2.3 + j * 1.9 == 82.3 && i < j) {
                    System.out.println("小明买了啤酒 " + i + " 瓶。");
                }
            }
        }
    }

}
