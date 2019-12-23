package com.hnq.study.bluebridgecup;

/**
 * 硬币方案
 * 有50枚硬币，可能包括4种类型：1元，5角，1角，5分。
 * 已知总价值为20元。求各种硬币的数量。
 * 【注意有50枚硬币】
 *
 * @author henengqiang
 * @date 2019/06/14
 */
public class CoinProgram {

    /**
     * 5分
     */
    private static final Integer FIVE_PENNY = 5;

    /**
     * 1角
     */
    private static final Integer ONE_CENT = FIVE_PENNY * 2;

    /**
     * 5角
     */
    private static final Integer FIVE_CENT = ONE_CENT * 5;

    /**
     * 一元
     */
    private static final Integer ONE_YUAN = FIVE_CENT * 2;

    private static final Integer TWENTY_YUAN = ONE_YUAN * 20;

    public static void main(String[] args) {
        searchCoinProgram();
    }

    private static void searchCoinProgram() {
        int count = 0;
        int limit = 50;
        for (int i = 0; i <= limit; i++) {
            for (int j = 0; j <= limit - i; j++) {
                for (int k = 0; k <= limit - j - i; k++) {
                    for (int l = 0; l <= limit - k - j - i; l++) {
                        if (i * FIVE_PENNY + j * ONE_CENT + k * FIVE_CENT + l * ONE_YUAN == TWENTY_YUAN && (i + j + k + l) == 50) {
                            System.out.println(String.format("5分：%s，1角：%s，5角：%s，1元：%s，总价值：20元", i, j , k, l));
                            count++;
                        }
                    }
                }
            }
        }
        System.out.println("总共有【" + count + "】种方案");
    }

}
