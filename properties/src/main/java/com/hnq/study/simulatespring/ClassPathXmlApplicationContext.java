package com.hnq.study.simulatespring;

import com.google.common.collect.Maps;
import com.hnq.toolkit.util.FileUtils;
import com.hnq.toolkit.util.XmlReader;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author henengqiang
 * @date 2019/06/18
 */
public class ClassPathXmlApplicationContext implements BeanFactory {

    /**
     * 启动程序时先把配置文件的数据读取到容器里，需要使用时再取出来
     */
    private Map<String, Object> beans = Maps.newHashMap();

    public ClassPathXmlApplicationContext(String xmlFileName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        File xmlFile = new File(FileUtils.getResourceFilePath(ClassPathXmlApplicationContext.class, xmlFileName));
        XmlReader xmlReader = new XmlReader(xmlFile);
        Document doc = xmlReader.getDocument();
        Element root = doc.getRootElement();
        List list = root.elements();
        for (Object o : list) {
            Element element = (Element) o;
            String id = element.attributeValue("id");
            String clazz = element.attributeValue("class");
            System.out.println(id + ":" + clazz);
            // newInstance()默认调用无参构造器
            Object obj = Class.forName(clazz).newInstance();
            beans.put(id, obj);
        }
    }

    @Override
    public Object getBean(String name) {
        return beans.get(name);
    }

}
