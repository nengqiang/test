package com.hnq.test.generator;

import com.github.cage.IGenerator;
import com.hnq.test.generate.CageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;
import java.util.Random;


/**
 * @author henengqiang
 * @date 2019/02/11
 */
@Component
public class MyFontGenerator implements IGenerator<Font> {

    @Autowired
    private CageConfig config;

    @Override
    public Font next() {
        int fontSize = config.getFontSize();
        List<String> fontNames = config.getFontNames();
        // 获取随机字体
        Random random = new Random();
        int n = random.nextInt(fontNames.size());
        return new Font(fontNames.get(n), Font.PLAIN, fontSize);
    }

}
