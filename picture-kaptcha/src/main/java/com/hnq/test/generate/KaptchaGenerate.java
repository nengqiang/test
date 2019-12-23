package com.hnq.test.generate;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.hnq.test.config.KaptchaParamConfig;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author henengqiang
 * @date 2018/12/3
 */
@Component
public class KaptchaGenerate {

    @Autowired
    private KaptchaParamConfig config;

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    public void generateVerifyCodePic() throws IOException {
        byte[] captchaChallengeAsJpeg;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        String createText = defaultKaptcha.createText();
        // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
        BufferedImage challenge = defaultKaptcha.createImage(createText);
        ImageIO.write(challenge, "png", jpegOutputStream);
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();

        String file = config.getFilePath() + File.separator + createText + "_" + System.currentTimeMillis() + ".png";
        FileUtils.writeByteArrayToFile(new File(file), captchaChallengeAsJpeg);
    }

}
