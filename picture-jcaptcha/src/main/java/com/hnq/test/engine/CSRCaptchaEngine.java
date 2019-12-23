package com.hnq.test.engine;

import com.hnq.test.generator.MyRandomWordGenerator;
import com.jhlabs.image.WaterFilter;
import com.jhlabs.image.WeaveFilter;
import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomListColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByFilters;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.fontgenerator.TwistedRandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.NonLinearTextPaster;
import com.octo.captcha.component.image.textpaster.SimpleTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.BaffleTextDecorator;
import com.octo.captcha.component.image.textpaster.textdecorator.LineTextDecorator;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.ImageFilter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author henengqiang
 * @date 2018/12/3
 */
@Component("com.hnq.test.engine.cSRCaptchaEngine")
public class CSRCaptchaEngine extends ListImageCaptchaEngine {

    private Integer minWordLength;

    private Integer maxWordLength;

    private Integer minFontSize;

    private Integer maxFontSize;

    private Integer imageWidth;

    private Integer imageHeight;

    private String acceptedChars;

    private String fontName;

    private final JcaptchaConfig config;

    @Autowired
    public CSRCaptchaEngine(JcaptchaConfig config) {
        this.config = config;
    }

    @Override
    protected void buildInitialFactories() {

        /*this.minWordLength = config.getMinWordLength();
        this.maxWordLength = config.getMaxWordLength();
        this.minFontSize = config.getMinFontSize();
        this.maxFontSize = config.getMaxFontSize();
        this.imageWidth = config.getImageWidth();
        this.imageHeight = config.getImageHeight();
        this.acceptedChars = config.getAcceptedChars();
        this.fontName = config.getFontNames().get(1);*/

        String fontNames = "Times New Roman,Consolas,Courier New,serif,Arial,Comic Sans MS,Calibri,Garamond,Didot,Optima,Andale Mono,Georgia,Verdana,Impact,Trajan,Asimov";
        List<String> fontNameList = Arrays.asList(fontNames.split(","));
        // 获取随机字体
        Random random = new Random();
        int n = random.nextInt(fontNameList.size());

        this.minWordLength = 4;
        this.maxWordLength = 4;
        this.minFontSize = 28;
        this.maxFontSize = 30;
        this.imageWidth = 100;
        this.imageHeight = 60;
        this.acceptedChars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.fontName = fontNameList.get(n);

        // 设置取词的范围，设置一定范围，或从本地词库读取
        WordGenerator wordGenerator = new MyRandomWordGenerator(acceptedChars);

        TextPaster randomPaster;
        int testPasterSelector = random.nextInt(3);
        switch (testPasterSelector) {
            case 1: randomPaster = getNonLinearTextPaster(); break;
            case 2: randomPaster = getSimpleTextPaster(); break;
            default: randomPaster = getDecoratedRandomTextPaster();
        }

        BackgroundGenerator background = getUniColorBackgroundGenerator();

        FontGenerator font = random.nextInt(2) == 0 ? getRandomFontGenerator() : getTwistedRandomFontGenerator();

        // build filters
        WaterFilter water = new WaterFilter();
        // 振幅
        water.setAmplitude(3d);
        // 锯齿或平滑
        water.setAntialias(true);
        // 相位
        water.setPhase(20d);
        // 波长
        water.setWavelength(70d);

        // backgroundDeformation 背景图变形
        ImageDeformation backDef = new ImageDeformationByFilters(random.nextInt(2) == 0 ? new ImageFilter[] {getWeaveFilter()} : new ImageFilter[] {});
        // textDeformation 字符图层转变形
        ImageDeformation textDef = new ImageDeformationByFilters(new ImageFilter[] {});
        // finalDeformation  最终图片变形
        ImageDeformation postDef = new ImageDeformationByFilters(new ImageFilter[] {});

        // 将FontGenerator、BackgroundGenerator、TextPaster、ImageDeformation和ImageFilter组装到一起，属于中间环节的类，类似一个容器
        WordToImage word2image = new DeformedComposedWordToImage(font, background, randomPaster, backDef, textDef, postDef);
        addFactory(new GimpyFactory(wordGenerator, word2image));
    }

    // --------------------------------------------------------------- TextPaster
    // 设置单词的最小最大长度，设置字的颜色，设置单词在图像中的位置是固定还是随机

    private TextPaster getDecoratedRandomTextPaster() {
        Random r = new Random();
        //文字干扰器 --- 可以创建多个
        // 气泡干扰
        BaffleTextDecorator baffleTextDecorator = new BaffleTextDecorator(1, Color.WHITE);
        // 曲线干扰
        LineTextDecorator lineTextDecorator = new LineTextDecorator(1,
                new RandomListColorGenerator(new Color[] {
                        new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256))
                }), 2);
        return new DecoratedRandomTextPaster(
                minWordLength,
                maxWordLength,
                    new RandomListColorGenerator(new Color[] {
                            new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256))
                    }),
                    r.nextInt(2) == 0 ? new TextDecorator[] { r.nextInt(2) == 0 ? baffleTextDecorator : lineTextDecorator }
                    : new TextDecorator[] {}
                );
    }

    private TextPaster getNonLinearTextPaster() {
        Random r = new Random();
        return new NonLinearTextPaster(
                minWordLength,
                maxWordLength,
                new RandomListColorGenerator(new Color[] {
                        new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256))
                }),
                true
        );
    }

    private TextPaster getSimpleTextPaster() {
        Random r = new Random();
        return new SimpleTextPaster(
                minWordLength,
                maxWordLength,
                new RandomListColorGenerator(new Color[] {
                        new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256))
                })
        );
    }

    // --------------------------------------------------------------- BackgroundGenerator
    // 设置背景颜色，图片大小

    private BackgroundGenerator getUniColorBackgroundGenerator() {
        return new UniColorBackgroundGenerator(imageWidth, imageHeight, Color.white);
    }

    // --------------------------------------------------------------- FontGenerator
    // 设置字体随机大小范围，字体名称

    /**
     * 设置随机出现的字体样式和随机的字体大小范围
     */
    private FontGenerator getRandomFontGenerator() {
        return new RandomFontGenerator(
                minFontSize,
                maxFontSize,
                new Font[] { new Font(fontName, Font.PLAIN, minFontSize) }
        );
    }

    /**
     * 设置字体的随机大小范围，并对字体进行简单的扭曲变形，字体左右倾斜的效果
     */
    private FontGenerator getTwistedRandomFontGenerator() {
        return new TwistedRandomFontGenerator(minFontSize, maxFontSize);
    }

    // --------------------------------------------------------------- ImageDeformation、ImageFilter
    // 非必须，指定为图片变形的类，可以用变形类和过滤器两种方式

    private ImageFilter getWeaveFilter() {
        return new WeaveFilter();
    }

}
