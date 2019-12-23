package com.hnq.study.simulatespring;

import com.hnq.toolkit.util.FileUtils;
import com.hnq.toolkit.util.XmlReader;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.File;
import java.util.List;

/**
 * @author henengqiang
 * @date 2019/06/18
 */
public class Sample {

    public static void main(String[] args) {
        readXmlSample();
    }

    private static void readXmlSample() {
        File xmlFile = new File(FileUtils.getResourceFilePath(Sample.class, "test.xml"));
        XmlReader xmlReader = new XmlReader(xmlFile);
        Document doc = xmlReader.getDocument();
        Element root = doc.getRootElement();
        List list = root.elements();
        for (Object o : list) {
            Element element = (Element) o;
            String name = element.attributeValue("name");
            String capacity = element.elementText("capacity");
            String directories = element.elementText("directories");
            String files = element.elementText("files");

            System.out.println("磁盘信息：");
            System.out.println("分区信息：" + name);
            System.out.println("分区容量：" + capacity);
            System.out.println("目录数：" + directories);
            System.out.println("文件数：" + files);
            System.out.println("--------------------");
        }
    }

}
