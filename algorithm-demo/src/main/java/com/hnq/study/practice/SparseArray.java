package com.hnq.study.practice;

/**
 * 稀疏数组
 *
 * rows      cols      val
 * 行数      列数       值的个数
 * 所在行下标 所在列下标 值
 * ...
 *
 * @author henengqiang
 * @date 2019/09/26
 */
public class SparseArray {

    public static void main(String[] args) {
        int[][] arr = new int[11][11];
        arr[1][2] = 1;
        arr[2][3] = -1;
        arr[4][5] = 6;
        System.out.println("二维数组：");
        print(arr);

        int[][] sparseArr = arrToSparseArr(arr);
        System.out.println("稀疏数组：");
        print(sparseArr);

        int[][] arr2 = sparseArrToArr(sparseArr);
        System.out.println("还原的二维数组：");
        print(arr2);

    }

    private static void print(int[][] arr) {
        if (arr == null) {
            System.out.println("null");
            return;
        }
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.printf("%4d", anInt);
            }
            System.out.println();
        }
        System.out.println("--------------------");
    }

    /**
     * 输入数组 0为默认值
     */
    private static int[][] arrToSparseArr(int[][] arr) {
        if (arr == null) {
            return null;
        }
        int val = 0;
        for (int[] ints : arr) {
            for (int anInt : ints) {
                if (anInt != 0) {
                    val++;
                }
            }
        }
        int[][] sparseArr = new int[val + 1][3];
        sparseArr[0][0] = arr[0].length;
        sparseArr[0][1] = arr.length;
        sparseArr[0][2] = val;

        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                    k++;
                    sparseArr[k][0] = i;
                    sparseArr[k][1] = j;
                    sparseArr[k][2] = arr[i][j];
                }
            }
        }
        return sparseArr;
    }

    private static int[][] sparseArrToArr(int[][] sparseArr) {
        if (sparseArr == null) {
            return null;
        }
        int[][] arr = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i <= sparseArr[0][2]; i++) {
            arr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        return arr;
    }

}
