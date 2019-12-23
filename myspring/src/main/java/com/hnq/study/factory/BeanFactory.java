package com.hnq.study.factory;

import com.hnq.study.bean.BeanMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author henengqiang
 * @date 2019/12/05
 */
public class BeanFactory {

    private static BeanFactory factory = null;

    private List<BeanMessage> beans = new ArrayList<>();

    public static BeanFactory getInstance() {
        if (factory == null) {
            factory = new BeanFactory();
        }
        return factory;
    }

    public BeanMessage gainBean(String target) {
        return beans.stream().map(bean -> bean.gainBeanMessage(target)).filter(Objects::nonNull).findFirst().orElse(null);
    }

    public List<BeanMessage> gainControllers() {
        return beans.stream().filter(BeanMessage::isController).collect(Collectors.toList());
    }

    public List<BeanMessage> gainServices() {
        return beans.stream().filter(BeanMessage::isService).collect(Collectors.toList());
    }

    public List<BeanMessage> gainAops() {
        return beans.stream().filter(BeanMessage::isAop).collect(Collectors.toList());
    }

    public boolean addBean(BeanMessage be) {
        return beans.add(be);
    }

    public List<BeanMessage> getBeans() {
        return beans;
    }

}
