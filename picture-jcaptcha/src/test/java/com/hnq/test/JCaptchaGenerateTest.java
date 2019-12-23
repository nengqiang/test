package com.hnq.test;

import com.hnq.test.engine.JcaptchaConfig;
import com.hnq.test.generate.JCaptchaGenerate;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author henengqiang
 * @date 2018/12/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JCaptchaCodeRecognizeApplication.class)
public class JCaptchaGenerateTest {

    @Autowired
    private JcaptchaConfig config;

    @Autowired
    private JCaptchaGenerate jCaptchaGenerate;

    @Test
    public void generateImagesTest() {
        int num = config.getPicNums();
        try {
            StopWatch watch = new StopWatch();
            watch.start();
            for (int i = 0; i < num; i++) {
                jCaptchaGenerate.generateVerifyCodePic();
            }
            watch.stop();

            System.err.println(String.format("生成【%s】张验证码图片在目录【%s】下，耗时 %s minutes (%s s)",
                    num, config.getFilePath(), watch.getTime() / 1000.0 / 60.0, watch.getTime() / 1000));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
