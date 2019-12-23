package com.hnq.study.util;

import java.util.Random;

/**
 * @author henengqiang
 * @date 2019/05/08
 */
public class GenUtils {

    public static String generateTaskId(int num) {
        Random r = new Random();
        StringBuilder taskId = new StringBuilder();
        for (int i = 0; i < num; i++) {
            taskId.append(r.nextInt(10));
            // 首位不能为0
            if (i == 0 && taskId.charAt(0) == '0') {
                taskId.replace(0, 1, "1");
            }
        }
        return taskId.toString();
    }

}
