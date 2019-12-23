package com.hnq.study;

import java.util.Properties;

/**
 * Java虚拟机有自己的系统配置文件（system.properties）
 *
 * @author henengqiang
 * @date 2019/04/08
 */
public class ReadJvm {

    public static void main(String[] args) {
        Properties prop = System.getProperties();
        prop.list(System.out);
    }

}
