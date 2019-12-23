package com.hnq.test;

import com.hnq.test.config.KaptchaParamConfig;
import com.hnq.test.generate.KaptchaGenerate;
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
@SpringBootTest(classes = KaptchaCodeRecognizeApplication.class)
public class KaptchaGenerateTest {

    @Autowired
    private KaptchaParamConfig config;

    @Autowired
    private KaptchaGenerate kaptchaGenerate;

    @Test
    public void generateImagesTest() {
        try {
            StopWatch watch = new StopWatch();
            watch.start();
            int nums = config.getPicNums();
            for (int i = 0; i < nums; i++) {
                kaptchaGenerate.generateVerifyCodePic();
            }
            watch.stop();

            System.err.println(String.format("生成【%s】张验证码图片在目录【%s】下，耗时 %s minutes (%s s)",
                    nums, config.getFilePath(), watch.getTime() / 1000.0 / 60.0, watch.getTime() / 1000));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void configTest() {
        System.out.println(config);
    }

}
