package com.hnq.practice.singlepattern.practice02;


import java.io.IOException;
import java.util.Properties;

/**
 * @author henengqiang
 * @date 2019/09/26
 */
public class Singleton03 {

    public static final Singleton03 INSTANCE;

    private String info;

    static {
        Properties pro = new Properties();
        try {
            pro.load(Singleton03.class.getClassLoader().getResourceAsStream("single.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        INSTANCE = new Singleton03(pro.getProperty("info"));
    }

    private Singleton03(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
