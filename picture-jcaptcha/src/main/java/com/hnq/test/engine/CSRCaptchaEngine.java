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
        // ??????????????????
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

        // ?????????????????????????????????????????????????????????????????????
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
        // ??????
        water.setAmplitude(3d);
        // ???????????????
        water.setAntialias(true);
        // ??????
        water.setPhase(20d);
        // ??????
        water.setWavelength(70d);

        // backgroundDeformation ???????????????
        ImageDeformation backDef = new ImageDeformationByFilters(random.nextInt(2) == 0 ? new ImageFilter[] {getWeaveFilter()} : new ImageFilter[] {});
        // textDeformation ?????????????????????
        ImageDeformation textDef = new ImageDeformationByFilters(new ImageFilter[] {});
        // finalDeformation  ??????????????????
        ImageDeformation postDef = new ImageDeformationByFilters(new ImageFilter[] {});

        // ???FontGenerator???BackgroundGenerator???TextPaster???ImageDeformation???ImageFilter???????????????????????????????????????????????????????????????
        WordToImage word2image = new DeformedComposedWordToImage(font, background, randomPaster, backDef, textDef, postDef);
        addFactory(new GimpyFactory(wordGenerator, word2image));
    }

    // --------------------------------------------------------------- TextPaster
    // ???????????????????????????????????????????????????????????????????????????????????????????????????????????????

    private TextPaster getDecoratedRandomTextPaster() {
        Random r = new Random();
        //??????????????? --- ??????????????????
        // ????????????
        BaffleTextDecorator baffleTextDecorator = new BaffleTextDecorator(1, Color.WHITE);
        // ????????????
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
    // ?????????????????????????????????

    private BackgroundGenerator getUniColorBackgroundGenerator() {
        return new UniColorBackgroundGenerator(imageWidth, imageHeight, Color.white);
    }

    // --------------------------------------------------------------- FontGenerator
    // ?????????????????????????????????????????????

    /**
     * ???????????????????????????????????????????????????????????????
     */
    private FontGenerator getRandomFontGenerator() {
        return new RandomFontGenerator(
                minFontSize,
                maxFontSize,
                new Font[] { new Font(fontName, Font.PLAIN, minFontSize) }
        );
    }

    /**
     * ?????????????????????????????????????????????????????????????????????????????????????????????????????????
     */
    private FontGenerator getTwistedRandomFontGenerator() {
        return new TwistedRandomFontGenerator(minFontSize, maxFontSize);
    }

    // --------------------------------------------------------------- ImageDeformation???ImageFilter
    // ????????????????????????????????????????????????????????????????????????????????????

    private ImageFilter getWeaveFilter() {
        return new WeaveFilter();
    }

}
