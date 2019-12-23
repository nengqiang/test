package com.hnq.study.tenquestions;

/**
 * 简单凯撒密码
 * 凯撒密码是啥？简单来说：就是通过移位来进行加密
 *
 * 比如，A-->B,B-->C,C-->D.......
 * 上面就是最简单的凯撒密码，将所有的字母进行移一位，实现加密
 *
 * @author henengqiang
 * @date 2018/12/12
 */
public class MoveEncode {

    private static final int A = 'A';
    private static final int B = 'B';
    private static final int Y = 'Y';
    private static final int Z = 'Z';
    private static int a = 'a';
    private static int b = 'b';
    private static int y = 'y';
    private static int z = 'z';

    public static void main(String[] args) {
        String s = "Hello World!";
        System.out.println("The origin message: " + s);

        char[] ch = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            ch[i] = (char) encode(s.charAt(i), 3);
        }
        System.out.println("The message has been encoded: ");
        System.out.println(ch);

        for (int i = 0; i < s.length(); i++) {
            ch[i] = (char) encode(ch[i], -3);
        }
        System.out.println("The message has been decoded: ");
        System.out.println(ch);
    }

    /**
     * 移位加密
     * @param ch        字符的ASCII数值
     * @param shift     为正向右移动shift位，为负向左移动shift位，为0不移动
     */
    private static int encode(int ch, int shift) {
        if (shift > 0) {
            for (int i = 0; i < shift; i++) {
                ch = rotateRight(ch);
            }
            return ch;
        } else if (shift < 0) {
            for (int i = 0; i < -shift; i++) {
                ch = rotateLeft(ch);
            }
            return ch;
        } else {
            return ch;
        }
    }

    /**
     * 向右移动一位
     * @param ch    字符的ASCII数值
     * @return      输入字符不在大小写26个字母范围内直接返回
     */
    private static int rotateRight(int ch) {
        if (ch >= A && ch <= Y) {
            return ch + 1;
        } else if (ch >= a && ch <= y) {
            return ch + 1;
        } else if (ch == Z) {
            return A;
        } else if (ch == z) {
            return a;
        } else {
            return ch;
        }
    }

    /**
     * 向右移动一位
     * @param ch    字符的ASCII数值
     * @return      输入字符不在大小写26个字母范围内直接返回
     */
    private static int rotateLeft(int ch) {
        if (ch >= B && ch <= Z) {
            return ch - 1;
        } else if (ch >= b && ch <= z) {
            return ch - 1;
        } else if (ch == A) {
            return Z;
        } else if (ch == a) {
            return z;
        } else {
            return ch;
        }
    }

}
