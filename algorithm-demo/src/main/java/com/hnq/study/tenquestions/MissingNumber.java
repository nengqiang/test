package com.hnq.study.tenquestions;

/**
 * 给一个数组{0,1,2,3,....n}，其中有一个数字缺失，请把缺失的数字找出来
 *
 * 思路：
 *  创建一个数组(题目数组的长度+1，因为题目的数组缺失了一个)
 *  创建的数组元素用特殊的符号(数字)来进行填满
 *  将题目给出的数组遍历并填充到创建的数组上，用index(0,1,2,3..)替代
 *  最后遍历创建的数组，哪个还是特殊的符号就是缺失的数字，返回index(缺失的数字)即可
 *
 *  优化，利用数学公式： (a1 + an) * n / 2
 *  假设我们没有缺失数字，等差求和公式可以快速得出答案。比如：{0,1,2,3}--->(0+3)*4/2--->6，
 *  如果此时缺失的是2呢，就是说题目的给出的数组是:{0,1,3}，我们利用等差公式求和之后减去数组每个元素，最后剩下的数就是缺失的数字！6-1-3-0--->2
 *
 * @author henengqiang
 * @date 2018-12-11
 */
public class MissingNumber {

    public static void main(String[] args) {
        int[] array = new int[]{0, 1, 2, 4, 5, 6, 7, 8, 9};
        int missingNumber1 = findMissingNumberA(array);
        System.out.println("Get MissingNumber By A: " + missingNumber1);
        int missingNumber2 = findMissingNumberB(array);
        System.out.println("Get MissingNumber By B: " + missingNumber2);
    }

    //--- 这两种方法都是在数组有缺失number的前提下，若没有缺失number会出bug ---//

    /**
     * 找到缺失的number
     * @param array  给定的数组
     * @return       返回-1表示不存在
     */
    private static int findMissingNumberA(int[] array) {
        int[] newArray = new int[array.length + 1];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = Integer.MAX_VALUE;
        }
        for (int arrForIndex : array) {
            newArray[arrForIndex] = arrForIndex;
        }
        for (int i = 0; i < newArray.length; i++) {
            if (newArray[i] == Integer.MAX_VALUE) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 找到缺失的number
     * @param array  给定的数组
     * @return       返回-1表示不存在
     */
    private static int findMissingNumberB(int[] array) {
        int sum = (array[0] + array[array.length - 1]) * (array.length + 1) / 2;
        for (int arr : array) {
            sum -= arr;
        }
        return sum == 0 ? -1 : sum;
    }

}
