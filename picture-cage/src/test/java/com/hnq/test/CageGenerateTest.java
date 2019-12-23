package com.hnq.test;

import com.hnq.test.generate.CageConfig;
import com.hnq.test.generate.CageGenerate;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author henengqiang
 * @date 2018/12/4
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CageCodeRecognizeApplication.class)
public class CageGenerateTest {

    @Autowired
    private CageGenerate cageGenerate;

    @Autowired
    private CageConfig config;

    @Test
    public void generateImagesTest() {
        StopWatch watch = new StopWatch();
        watch.start();
        int nums = config.getPicNums();
        for (int i = 0; i < nums; i++) {
            cageGenerate.generateVerifyCodePic();
        }
        watch.stop();

        System.err.println(String.format("生成【%s】张验证码图片在目录【%s】下，耗时 %s minutes (%s s)",
                nums, config.getFilePath(), watch.getTime() / 1000.0 / 60.0, watch.getTime() / 1000));
    }

    @Test
    public void configTest() {
        System.out.println(config);
    }

}
