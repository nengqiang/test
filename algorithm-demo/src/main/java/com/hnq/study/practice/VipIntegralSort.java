package com.hnq.study.practice;

import com.google.common.collect.Maps;
import com.hnq.toolkit.util.SortUtils;

import java.util.Map;
import java.util.Scanner;

/**
 * 题目描述：
 * 元旦佳节快到了，超市A想要给会员一些奖品。但是奖品有限，所以它需要给这些会员做一个排序，然后将名单输出来。排序的规则是，先按积分排序，
 * 如果会员的积分相同则按他的ID排序，因为ID号小则表示他注册的时间早。
 * 现在某超市有每个会员的ID（ID保证不重复）、积分，它想要请你帮忙给排个序。
 *
 * @author henengqiang
 * @date 2019/06/18
 */
public class VipIntegralSort {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        vipIntegralSortLogic();
    }

    private static void vipIntegralSortLogic() {
        System.out.println("请输入会员人数：");
        int num = sc.nextInt();
        int[] ids = SortUtils.genNotRepeatingArr(num, num * 2);
        int[] integral = SortUtils.generateArray(num, num / 2);
        Map<Integer, Integer> idIntegralMap = Maps.newLinkedHashMap();
        for (int i = 0; i < ids.length; i++) {
            idIntegralMap.put(ids[i], integral[i]);
        }

        idIntegralMap = SortUtils.sortMapByKey(idIntegralMap, true);
        idIntegralMap = SortUtils.sortByValueDesc(idIntegralMap);

        idIntegralMap.forEach((k, v) -> System.out.println("id=" + k + ", integral=" + v));
    }

}
