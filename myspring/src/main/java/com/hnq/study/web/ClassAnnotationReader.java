package com.hnq.study.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author henengqiang
 * @date 2019/12/05
 */
public class ClassAnnotationReader {

    public static List<Class> classes = new ArrayList<>();

    /**
     * 遍历当前项目的文件，获取所有class对象
     */
    public static void read() {
        try {
            File file = new File(ClassAnnotationReader.class.getResource("../").toURI());
            readFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readFile(File file) throws ClassNotFoundException {
        File[] files = file.listFiles();
        for (File f : Objects.requireNonNull(files)) {
            if (f.isDirectory()) {
                readFile(f);
            } else {
                String path = f.getPath().replace("\\", ".");
                if (!path.endsWith(".class")) {
                    continue;
                }
                String className = path.substring(path.lastIndexOf("classes.") + 8, path.lastIndexOf(".class"));
                classes.add(Class.forName(className));
            }
        }
    }

}
