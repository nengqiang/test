package com.hnq.test.webdetect;

import com.google.common.collect.Maps;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.compress.utils.Lists;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author henengqiang
 * @date 2019/11/04
 */
public class BuildHtml {

    public static void main(String[] args) {
        try {
            buildPage();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    private static void buildPage() throws IOException, TemplateException {
        List<TousuInfo> tousuInfos = Lists.newArrayList();

        TousuInfo info1 = new TousuInfo();
        info1.setPlatform("聚投诉");
        info1.setKeyword("宜人贷");
        info1.setTitle("停止催收");
        info1.setLink("http://www.baidu.com");
        info1.setTime(new Date());
        tousuInfos.add(info1);

        TousuInfo info2 = new TousuInfo();
        info2.setPlatform("聚投诉");
        info2.setKeyword("宜人贷");
        info2.setTitle("停止催收");
        info2.setLink("http://www.baidu.com");
        info2.setTime(new Date());
        tousuInfos.add(info2);

        TousuInfo info3 = new TousuInfo();
        info3.setPlatform("聚投诉");
        info3.setKeyword("功夫贷");
        info3.setTitle("停止催收");
        info3.setLink("http://www.baidu.com");
        info3.setTime(new Date());
        tousuInfos.add(info3);

        TousuInfo info4 = new TousuInfo();
        info4.setPlatform("黑猫投诉");
        info4.setKeyword("功夫贷");
        info4.setTitle("停止催收");
        info4.setLink("http://www.baidu.com");
        info4.setTime(new Date());
        tousuInfos.add(info4);

        Map<String, List<TousuInfo>> groupMap = tousuInfos.stream().collect(Collectors.groupingBy(TousuInfo::getKeyword));
        
        
        List<TousuSummary> summaries = Lists.newArrayList();
        
        TousuSummary summary1 = new TousuSummary();
        summary1.setDate(new Date());
        summary1.setMerchant("功夫贷");
        summary1.setJutousuNum(4);
        summary1.setBlackcatNum(2);
        summary1.setTotal(6);
        summaries.add(summary1);

        TousuSummary summary2 = new TousuSummary();
        summary2.setDate(new Date());
        summary2.setMerchant("你我贷");
        summary2.setJutousuNum(10);
        summary2.setBlackcatNum(5);
        summary2.setTotal(15);
        summaries.add(summary2);

        TousuSummary summary3 = new TousuSummary();
        summary3.setDate(new Date());
        summary3.setMerchant("宜人贷");
        summary3.setJutousuNum(8);
        summary3.setBlackcatNum(2);
        summary3.setTotal(10);
        summaries.add(summary3);
        

        Configuration configuration = new Configuration(Configuration.getVersion());
        // 指定configuration对象模板文件存放的路径
        InputStream ins = BuildHtml.class.getResourceAsStream("");
//        configuration.setDirectoryForTemplateLoading(new File());
        configuration.setTemplateLoader(new ClassTemplateLoader(BuildHtml.class, "/"));
        configuration.setDefaultEncoding("UTF-8");
        Template template = configuration.getTemplate("template.ftl");

        Map<String, Object> map = Maps.newHashMap();
        map.put("dataMap", groupMap);
        map.put("dataList", summaries);

        StringWriter writer1 = new StringWriter();
        template.process(map, writer1);
        System.out.println(writer1.toString());
        writer1.close();
    }

}
