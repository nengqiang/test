package com.hnq.study.practice;

import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 公式：
 * F(N, M) = min{ F1(N, M), F2(N, M) ... FM(N, M) }
 * Fk(N, M) = 1 + max{ F(N - 1, K - 1), F(N, M - k) } (0 < k <= M)
 * <p>
 * 从K层扔下，要么碎，剩下 k - 1 层楼；要么没碎，剩下 M - k 层楼
 * <p>
 * dp(N, M) = 1≤X≤M min(1 + max(dp(N−1, X−1), dp(N, M−X)))
 *
 * @author henengqiang
 * @date 2020/4/8
 */
public class BottleDrop {

    public static void main(String[] args) {
        StopWatch watch = new StopWatch();
        watch.start();
        System.out.println(drop2(20, 10000));
        watch.stop();
        System.out.println(watch.getTotalTimeMillis());
    }

    private static Map<String, Integer> dic = new HashMap<>(16);

    /**
     * 计算N个瓶子，M层楼，在最坏的情况下，最少扔多少次可以测出临界点
     * 瓶子扔下去只有碎或者不碎两种状态，没碎的瓶子可以完好无损的再利用
     *
     * @param bottles 瓶子个数
     * @param floors  楼层数
     * @return        最坏的情况下，最少扔的次数
     */
    private static int drop(int bottles, int floors) {
        if (floors < 1 && bottles < 1) {
            return 0;
        }
        if (bottles == 1) {
            return floors;
        }
        int[] res = new int[floors];
        for (int i = 1; i <= floors; i++) {
            String k1 = "f" + (bottles - 1) + (i - 1);
            String k2 = "f" + bottles + (floors - i);
            if (dic.containsKey(k1)) {
                if (dic.containsKey(k2)) {
                    res[i - 1] = Math.max(dic.get(k1), dic.get(k2)) + 1;
                } else {
                    int right = drop(bottles, floors - i);
                    res[i - 1] = Math.max(dic.get(k1), right) + 1;
                    dic.put(k2, right);
                }
            } else {
                int left = drop(bottles - 1, i - 1);
                if (dic.containsKey(k2)) {
                    res[i - 1] = Math.max(left, dic.get(k2)) + 1;
                } else {
                    int right = drop(bottles, floors - i);
                    res[i - 1] = Math.max(left, right) + 1;
                    dic.put(k1, left);
                    dic.put(k2, right);
                }
            }
        }
        return Arrays.stream(res).reduce(Math::min).orElse(0);
    }

    /**################################################################################################################
     *#                                                                                                               #
     *#                                                    drop2                                                      #
     * ##############################################################################################################**/

    private static int drop2(int bottles, int floors) {
        return dr(bottles, floors);
    }

    private static Map<Integer, Integer> ME = new HashMap<>(16);

    private static int dr(int n, int m) {
        if (!ME.containsKey(m * 100 + n)) {
            int ans;
            if (m == 0) {
                ans = 0;
            } else if (n == 1) {
                ans = m;
            } else {
                int lo = 1, hi = m;
                while (lo + 1 < hi) {
                    int x = (lo + hi) / 2;
                    int t1 = dr(n - 1, x - 1);
                    int t2 = dr(n, m - x);

                    if (t1 < t2) {
                        lo = x;
                    } else if (t1 > t2) {
                        hi = x;
                    } else {
                        lo = hi = x;
                    }
                }

                ans = 1 + Math.min(Math.max(dr(n - 1, lo - 1), dr(n, m - lo)),
                                    Math.max(dr(n - 1, hi - 1), dr(n, m - hi)));
            }

            ME.put(m * 100 + n, ans);
        }

        return ME.get(m * 100 + n);
    }

    /**################################################################################################################
     *#                                                                                                               #
     *#                                                    drop3                                                      #
     * ##############################################################################################################**/

    private static int drop3(int bottles, int floors) {
        int[] dp = new int[floors + 1];
        for (int i = 0; i <= floors; ++i) {
            dp[i] = i;
        }

        for (int k = 2; k <= bottles; ++k) {
            int[] dp2 = new int[floors + 1];
            int x = 1;
            for (int n = 1; n <= floors; ++n) {
                while (x < n && Math.max(dp[x - 1], dp2[n - x]) > Math.max(dp[x], dp2[n - x - 1])) {
                    x++;
                }

                dp2[n] = 1 + Math.max(dp[x - 1], dp2[n - x]);
            }

            dp = dp2;
        }

        return dp[floors];
    }

    /**################################################################################################################
     *#                                                                                                               #
     *#                                                    drop4                                                      #
     * ##############################################################################################################**/

    private static int drop4(int bottles, int floors) {
        int lo = 1, hi = floors;
        while (lo < hi) {
            int mi = (lo + hi) / 2;
            if (f(mi, bottles, floors) < floors) {
                lo = mi + 1;
            } else {
                hi = mi;
            }
        }

        return lo;
    }

    private static int f(int x, int bottles, int floors) {
        int ans = 0, r = 1;
        for (int i = 1; i <= bottles; ++i) {
            r *= x - i + 1;
            r /= i;
            ans += r;
            if (ans >= floors) {
                break;
            }
        }
        return ans;
    }
    
}
