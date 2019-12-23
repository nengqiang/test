package com.hnq.test.generate;

import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.*;
import com.github.bingoohuang.patchca.font.RandomFontFactory;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * patchca
 *
 * @author henengqiang
 * @date 2018/12/3
 */
@Component
@SuppressWarnings("unchecked")
public class PatchcaGenerate {

    @Autowired
    private PatchcaConfig config;

    private ConfigurableCaptchaService cs = new ConfigurableCaptchaService();

    private Random random = new Random();

    public void generateVerifyCodePic() throws IOException {
        setCaptchaFactory();

        byte[] patchcaChallengeAsJpeg;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        String token = EncoderHelper.getChallangeAndWriteImage(cs, "png", jpegOutputStream);
        patchcaChallengeAsJpeg = jpegOutputStream.toByteArray();

        String file = config.getFilePath() + File.separator + token + "_" + System.currentTimeMillis() + ".png";
        FileUtils.writeByteArrayToFile(new File(file), patchcaChallengeAsJpeg);
    }

    private void setCaptchaFactory() {
        cs.setColorFactory(x -> {
            int[] c = {25, 60, 170};
            int i = random.nextInt(c.length);
            for (int fi = 0; fi < c.length; fi++) {
                if (fi == i) {
                    c[fi] = random.nextInt(71);
                } else {
                    c[fi] = random.nextInt(256);
                }
            }
            return new Color(c[0], c[1], c[2]);
        });
        RandomWordFactory wf = new RandomWordFactory();
        wf.setCharacters(config.getCharsets());
        wf.setMaxLength(config.getCodeLength());
        wf.setMinLength(config.getCodeLength());

        // 随机字体生成器
        RandomFontFactory fontFactory = new RandomFontFactory();
        fontFactory.setMaxSize(config.getMinFontSize());
        fontFactory.setMinSize(config.getMaxFontSize());
        List fontFamily = new ArrayList<String>();
        int n = random.nextInt(config.getFontNames().size());
        fontFamily.add(config.getFontNames().get(n));
        fontFactory.setFamilies(fontFamily);
        cs.setFontFactory(fontFactory);

        // 验证码图片的大小
        cs.setWidth(config.getImageWidth());
        cs.setHeight(config.getImageHeight());
        cs.setWordFactory(wf);

        // 随机干扰
        switch (random.nextInt(5)) {
            case 0:
                // 干扰线波纹
                cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
                break;
            case 1:
                // 大理石波纹
                cs.setFilterFactory(new MarbleRippleFilterFactory());
                break;
            case 2:
                // 双波纹
                cs.setFilterFactory(new DoubleRippleFilterFactory());
                break;
            case 3:
                // 摆波纹
                cs.setFilterFactory(new WobbleRippleFilterFactory());
                break;
            case 4:
                // 漫波纹
                cs.setFilterFactory(new DiffuseRippleFilterFactory());
                break;
            default:
                break;
        }
    }

}
