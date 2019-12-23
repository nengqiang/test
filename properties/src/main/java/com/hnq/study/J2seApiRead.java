package com.hnq.study;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * 使用J2SE API读取Properties文件的六种方法
 *
 * @author henengqiang
 * @date 2019/04/08
 */
public class J2seApiRead {

    private String nameWithoutSuffix = "test";

    private String onlyName = "test.properties";

    private String nameWithSeparator = "/test.properties";

    public static void main(String[] args) {
        int separatorNums = 50;
        J2seApiRead read = new J2seApiRead();
        read.read1();
        printSeparators(separatorNums);
        read.read2();
        printSeparators(separatorNums);
        read.read3();
        printSeparators(separatorNums);
        read.read4();
        printSeparators(separatorNums);
        read.read5();
        printSeparators(separatorNums);
        read.read6();
    }

    private String getResourcesFilePath() {
        return this.getClass().getResource(nameWithSeparator).getPath();
    }

    /**
     * {@link java.util.Properties#load(InputStream in)}
     */
    private void read1() {
        try {
            FileInputStream in = new FileInputStream(getResourcesFilePath());
            Properties pro = new Properties();
            pro.load(in);
            outputKeyValue(pro);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * {@link Class#getResourceAsStream(String name)}
     */
    private void read2() {
        Properties pro = new Properties();
        InputStream in = J2seApiRead.class.getResourceAsStream(nameWithSeparator);
        try {
            pro.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        outputKeyValue(pro);
    }

    /**
     * {@link Class#getClassLoader(),ClassLoader#getResourceAsStream(String name)}
     */
    private void read3() {
        Properties pro = new Properties();
        InputStream in = J2seApiRead.class.getClassLoader().getResourceAsStream(onlyName);
        try {
            pro.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        outputKeyValue(pro);
    }

    /**
     * {@link java.lang.ClassLoader#getSystemResourceAsStream(String name)}
     */
    private void read4() {
        Properties pro = new Properties();
        InputStream in = ClassLoader.getSystemResourceAsStream(onlyName);
        try {
            pro.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        outputKeyValue(pro);
    }

    /**
     * {@link java.util.ResourceBundle#getBundle(String)}
     */
    private void read5() {
        ResourceBundle rs = ResourceBundle.getBundle(nameWithoutSuffix);

        String name = rs.getString("name");
        String age = rs.getString("age");
        String sex = rs.getString("sex");
        String address = rs.getString("address");
        String phone = rs.getString("phone");

        System.out.println("name=" + name);
        System.out.println("age=" + age);
        System.out.println("sex=" + sex);
        System.out.println("address=" + address);
        System.out.println("phone=" + phone);
    }

    /**
     * {@link java.util.PropertyResourceBundle#PropertyResourceBundle(InputStream in)}
     */
    private void read6() {
        File file = new File(getResourcesFilePath());
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            PropertyResourceBundle prb = new PropertyResourceBundle(in);

            String name = prb.getString("name");
            String age = prb.getString("age");
            String sex = prb.getString("sex");
            String address = prb.getString("address");
            String phone = prb.getString("phone");

            System.out.println("name=" + name);
            System.out.println("age=" + age);
            System.out.println("sex=" + sex);
            System.out.println("address=" + address);
            System.out.println("phone=" + phone);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void outputKeyValue(Properties pro) {
        Enumeration em = pro.propertyNames();
        while (em.hasMoreElements()) {
            String key = (String) em.nextElement();
            String value = pro.getProperty(key);
            System.out.println(key + "=" + value);
        }
    }

    private static void printSeparators(int num) {
        for (int i = 0; i < num; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

}
