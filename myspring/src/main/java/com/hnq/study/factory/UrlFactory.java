package com.hnq.study.factory;

import com.hnq.study.bean.UrlMethodRelate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author henengqiang
 * @date 2019/12/05
 */
public class UrlFactory {

    private static UrlFactory factory = null;

    private List<UrlMethodRelate> urlMethodRelates = new ArrayList<>();

    public static UrlFactory getInstance() {
        if (factory == null) {
            factory = new UrlFactory();
        }
        return factory;
    }

    public boolean addUrlMethodRelate(UrlMethodRelate umr) {
        return urlMethodRelates.add(umr);
    }

    public UrlMethodRelate gainUrlMethodRelate(String url) {
        return urlMethodRelates.stream().filter(u -> u.getUrl().equals(url)).findFirst().orElse(null);
    }

}
