package com.hnq.test.generate;

import com.hnq.test.constants.WordsOfCode;
import com.hnq.test.engine.JcaptchaConfig;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * jcaptcha
 *
 * @author henengqiang
 * @date 2018/12/3
 */
@Component
public class JCaptchaGenerate {

    @Autowired
    private JcaptchaConfig config;

    @Autowired
    private GenericManageableCaptchaService captchaService;

    public void generateVerifyCodePic() throws IOException {
        byte[] captchaChallengeAsJpeg;
        // 输出jpg的字节流
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        String captchaId = "DED35D1D956212431220C211969B035A";
        BufferedImage challenge = captchaService.getImageChallengeForID(captchaId, Locale.SIMPLIFIED_CHINESE);

        // a jpeg encoder
        JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(jpegOutputStream);
        jpegEncoder.encode(challenge);
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();

        String file = config.getFilePath() + File.separator + WordsOfCode.code + "_" + System.currentTimeMillis() + ".png";
        FileUtils.writeByteArrayToFile(new File(file), captchaChallengeAsJpeg);
    }

}
