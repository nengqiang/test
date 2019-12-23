package com.hnq.study.practice;

/**
 * @author henengqiang
 * @date 2019/12/16
 */
public class GetMemory {

    public static void main(String[] args) {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();

        // 1 / 64 内存
        System.out.println("TOTAL_MEMORY(-Xms) = " + totalMemory + "字节 = " + totalMemory / (double) 1024 / 1024 + "MB");
        // 1/ 4 内存
        System.out.println("MAX_MEMORY(-Xmx) = " + maxMemory + "字节 = " + maxMemory / (double) 1024 / 1024 + "MB" );
    }

}
