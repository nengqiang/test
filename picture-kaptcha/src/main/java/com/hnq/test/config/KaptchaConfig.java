package com.hnq.test.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.Random;

/**
 * 该配置文件和 mykaptcha.xml 二选一
 *
 * 图片样式：
 * 水纹com.google.code.kaptcha.impl.WaterRipple
 * 鱼眼com.google.code.kaptcha.impl.FishEyeGimpy
 * 阴影com.google.code.kaptcha.impl.ShadowGimpy
 *
 * @author henengqiang
 * @date 2018/12/3
 */
@Component
public class KaptchaConfig {

    @Autowired
    private KaptchaParamConfig config;

    private Random r = new Random();

    @Bean
    public DefaultKaptcha getDefaultKaptcha() {
        com.google.code.kaptcha.impl.DefaultKaptcha defaultKaptcha = new com.google.code.kaptcha.impl.DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", config.getBorder());
        properties.setProperty("kaptcha.border.color", getRandomColorValue());
        properties.setProperty("kaptcha.textproducer.font.color", config.getFontColor());
        properties.setProperty("kaptcha.image.width", config.getImageWidth().toString());
        properties.setProperty("kaptcha.image.height", config.getImageHeight().toString());
        properties.setProperty("kaptcha.textproducer.font.size", config.getFontSize().toString());
        properties.setProperty("kaptcha.textproducer.char.string", config.getCharsets());
        // 文字间隔
//        properties.setProperty("kaptcha.textproducer.char.space", "6");
        // 图片样式
        properties.setProperty("kaptcha.obscurificator.impl", config.getPicStyle());
        properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.char.length", config.getCodeLength().toString());
        int n = r.nextInt(config.getFontNames().size());
        properties.setProperty("kaptcha.textproducer.font.names", config.getFontNames().get(n));
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }

    private String getRandomColorValue() {
        return r.nextInt(256) + "," + r.nextInt(256) + "," + r.nextInt(256);
    }

    private String getRandomPicStyle() {
        switch (r.nextInt(3)) {
            case 1: return "com.google.code.kaptcha.impl.WaterRipple";
            case 2: return "com.google.code.kaptcha.impl.FishEyeGimpy";
            default: return "com.google.code.kaptcha.impl.ShadowGimpy";
        }
    }

}
