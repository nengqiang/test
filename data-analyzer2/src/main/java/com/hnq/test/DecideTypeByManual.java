package com.hnq.test;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author henengqiang
 * @date 2019/11/21
 */
public class DecideTypeByManual {

    private static Scanner sc = new Scanner(System.in);

    private static final String EXIT = "exit";

    private static final String PATH_PREFIX = "/Users/hanif/studyProjects/test/data-analyzer2/src/main/resources/data/train/complaint_type/";

    private static final String LABEL_PATH = "/Users/hanif/studyProjects/test/data-analyzer2/src/main/resources/data/train/ticket_label/";

    private static final String OTHER = "其他";
    private static final String HIGH = "利费过高";
    private static final String DELAY = "延期还款";
    private static final String CREDIT = "征信记录";
    private static final String AHEAD = "提前结清";
    private static final String CONTACT = "撸通讯录";
    private static final String NO_ABILITY = "无能力还款";
    private static final String COLLECTION = "暴力催收";
    private static final String RELIEF = "费用减免";
    private static final String DELAY_HIGH = "逾期费用过高";
    private static final String AGREE = "协议问题";
    private static final String CANCEL = "取消贷款";

    private static final String REDUCE = "减免";
    private static final String CREDIT_TYPE = "征信类";
    private static final String AHEAD_PAY = "提前还款";
    private static final String DELAY_TYPE = "逾期和催收";
    private static final String FEE = "金额和费率";


    public static void main(String[] args) throws IOException {
        interaction();
    }

    private static void interaction() throws IOException {
        System.out.println("请输入从第几个投诉开始 exit-退出：");
        String in = sc.nextLine();
        checkExit(in);
        int start = Integer.parseInt(in);

        List<String> data = readData();
        for (int i = start - 1; i < data.size(); i++) {
            System.out.println(String.format("第【%d】个投诉， 共[%d]个，进度[%s]", i + 1, data.size(),
                    new BigDecimal((double)(i + 1) / (double)data.size() * 100).setScale(2, BigDecimal.ROUND_HALF_DOWN) + "%"));
            System.out.println(data.get(i));
            decideComplaintType(data.get(i));
            decideLabelType(data.get(i));
        }
    }
    
    private static void decideComplaintType(String data) throws IOException {
        System.out.println(String.format("请输入投诉类型：0-%s 1-%s 2-%s 3-%s 4-%s 5-%s 6-%s 7-%s 8-%s 9-%s a-%s b-%s(可多选) exit-退出",
                OTHER, HIGH, DELAY, CREDIT, AHEAD, CONTACT, NO_ABILITY, COLLECTION, RELIEF, DELAY_HIGH, AGREE, CANCEL));
        String in = sc.nextLine();
        checkExit(in);
        System.out.println("写入数据中...");
        String[] ins = in.split("");
        for (String s : ins) {
            String dir;
            switch (s) {
                case "0":
                    dir = PATH_PREFIX + OTHER;
                    writeData(data, dir + "/" + (getTxtFilesCount(dir) + 1) + ".txt");
                    break;
                case "1":
                    dir = PATH_PREFIX + HIGH;
                    writeData(data, dir + "/" + (getTxtFilesCount(dir) + 1) + ".txt");
                    break;
                case "2":
                    dir = PATH_PREFIX + DELAY;
                    writeData(data, dir + "/" + (getTxtFilesCount(dir) + 1) + ".txt");
                    break;
                case "3":
                    dir = PATH_PREFIX + CREDIT;
                    writeData(data, dir + "/" + (getTxtFilesCount(dir) + 1) + ".txt");
                    break;
                case "4":
                    dir = PATH_PREFIX + AHEAD;
                    writeData(data, dir + "/" + (getTxtFilesCount(dir) + 1) + ".txt");
                    break;
                case "5":
                    dir = PATH_PREFIX + CONTACT;
                    writeData(data, dir + "/" + (getTxtFilesCount(dir) + 1) + ".txt");
                    break;
                case "6":
                    dir = PATH_PREFIX + NO_ABILITY;
                    writeData(data, dir + "/" + (getTxtFilesCount(dir) + 1) + ".txt");
                    break;
                case "7":
                    dir = PATH_PREFIX + COLLECTION;
                    writeData(data, dir + "/" + (getTxtFilesCount(dir) + 1) + ".txt");
                    break;
                case "8":
                    dir = PATH_PREFIX + RELIEF;
                    writeData(data, dir + "/" + (getTxtFilesCount(dir) + 1) + ".txt");
                    break;
                case "9":
                    dir = PATH_PREFIX + DELAY_HIGH;
                    writeData(data, dir + "/" + (getTxtFilesCount(dir) + 1) + ".txt");
                    break;
                case "a":
                    dir = PATH_PREFIX + AGREE;
                    writeData(data, dir + "/" + (getTxtFilesCount(dir) + 1) + ".txt");
                    break;
                case "b":
                    dir = PATH_PREFIX + CANCEL;
                    writeData(data, dir + "/" + (getTxtFilesCount(dir) + 1) + ".txt");
                    break;
                default:
                    System.err.println("没有匹配到任何投诉类型");
                    break;
            }
        }
        System.out.println("投诉类型数据写入完毕。");
    }
    
    private static void decideLabelType(String data) throws IOException {
        System.out.println(String.format("请输入标签类型：0-%s 1-%s 2-%s 3-%s 4-%s 5-%s exit-退出", OTHER, REDUCE, CREDIT_TYPE, AHEAD_PAY, DELAY_TYPE, FEE));
        String in = sc.nextLine();
        checkExit(in);
        String dir;
        switch (in) {
            case "0":
                dir = LABEL_PATH + OTHER;
                writeData(data, dir + "/" + (getTxtFilesCount(dir) + 1) + ".txt");
                break;
            case "1":
                dir = LABEL_PATH + REDUCE;
                writeData(data, dir + "/" + (getTxtFilesCount(dir) + 1) + ".txt");
                break;
            case "2":
                dir = LABEL_PATH + CREDIT_TYPE;
                writeData(data, dir + "/" + (getTxtFilesCount(dir) + 1) + ".txt");
                break;
            case "3":
                dir = LABEL_PATH + AHEAD_PAY;
                writeData(data, dir + "/" + (getTxtFilesCount(dir) + 1) + ".txt");
                break;
            case "4":
                dir = LABEL_PATH + DELAY_TYPE;
                writeData(data, dir + "/" + (getTxtFilesCount(dir) + 1) + ".txt");
                break;
            case "5":
                dir = LABEL_PATH + FEE;
                writeData(data, dir + "/" + (getTxtFilesCount(dir) + 1) + ".txt");
                break;
            default:
                System.err.println("没有匹配到任何标签");
                break;
        }
        System.out.println("标签类型写入完毕。");
    }

    private static void checkExit(String in) {
        if (EXIT.equals(in)) {
            System.exit(0);
        }
    }

    private static List<String> readData() throws IOException {
        InputStream pr = DecideTypeByManual.class.getClassLoader().getResourceAsStream("data/origin/qz5.json");
        String practice = IOUtils.toString(Objects.requireNonNull(pr), Charsets.UTF_8);
        List<Map> maps = JSON.parseArray(practice, Map.class);
        return maps.stream().filter(m -> !m.get("overview").toString().contains("..."))
                .map(m -> StringUtils.isBlank(m.get("appeal").toString()) ? m.get("title") + "\n" + m.get("overview")
                        : m.get("title") + "\n" + m.get("appeal") + "\n" + m.get("overview")).collect(Collectors.toList());
    }

    private static void writeData(String data, String path) throws IOException {
        File file = new File(path);
        FileUtils.writeStringToFile(file, data, Charsets.UTF_8);
    }

    private static int getTxtFilesCount(String path) {
        int count = 0;
        File srcFile = new File(path);
        File[] files = srcFile.listFiles();
        for (File f : Objects.requireNonNull(files)) {
            if (f.getName().endsWith(".txt")) {
                count++;
            }
        }
        // 返回.txt文件个数
        return count;
    }

}
