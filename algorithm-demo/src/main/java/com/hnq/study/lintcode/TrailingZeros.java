package com.hnq.study.lintcode;

/**
 * @author henengqiang
 * @date 2019/09/17
 */
public class TrailingZeros {

    public static void main(String[] args) {
        System.out.println(trailingZeros(4));
        System.out.println(trailingZeros(6));
        System.out.println(trailingZeros(8));
        System.out.println(trailingZeros(11));
        System.out.println(trailingZeros(20));
    }

    /**
     * 直接求阶乘，然后数0的方法复杂度过高，根本无法运行。
     *
     * 我们知道，任何数的阶乘都可以转化为一系列质数相乘. 计算尾部0的个数，就可以转换为求2x5的个数。而只要是偶数都含有2，故可直接数5的个数。
     */
    private static long trailingZeros(long n) {
        long ans = 0;
        while (n >= 5) {
            ans += n / 5;
            n /= 5;
        }
        return ans;
    }

}
