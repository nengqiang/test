package com.hnq.study.leetcode;

/**
 * 对于一个给定的 source 字符串和一个 target 字符串，你应该在 source 字符串中找出 target 字符串出现的第一个位置(从0开始)。如果不存在，则返回 -1。
 *
 * 样例
 * 样例 1:
 *
 * 输入: source = "source" ， target = "target"
 * 输出:-1
 * 样例解释: 如果source里没有包含target的内容，返回-1
 * 样例 2:
 *
 * 输入: source = "abcdabcdefg" ，target = "bcd"
 * 输出: 1
 * 样例解释: 如果source里包含target的内容，返回target在source里第一次出现的位置
 * 挑战
 * O(n^2)的算法是可以接受的。如果你能用O(n)的算法做出来那更加好。（提示：KMP）
 *
 * @author henengqiang
 * @date 2020/3/13
 */
public class StrInStr {

    public static void main(String[] args) {
        System.out.println(strStr("source", "target"));
        System.out.println(strStr("abcdefg", "bcd"));
        System.out.println(strStr("a", "a"));
        System.out.println(strStr("abcde", "e"));
        System.out.println(strStr("", ""));
        System.out.println(strStr("tartarget", "target"));
    }

    /**
     *
     * @param source
     * @param target
     * @return          return the index
     */
    private static int strStr(String source, String target) {
        // Write your code here
        if (source == null || target == null || !source.contains(target)) {
            return -1;
        }
        if (target.length() < 1) {
            return 0;
        }
        if (target.length() == 1) {
            return source.indexOf(target);
        }
        char[] sources = source.toCharArray();
        char[] targets = target.toCharArray();
        for (int i = 0; i < sources.length; i++) {
            if (targets[0] == sources[i]) {
                int k = i + 1;
                for (int j = 1; j < targets.length; j++) {
                    if (targets[j] != sources[k++]) {
                        break;
                    }
                    if (j == targets.length - 1) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

}
