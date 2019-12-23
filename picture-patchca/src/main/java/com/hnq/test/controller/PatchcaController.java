package com.hnq.test.controller;

import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.*;
import com.github.bingoohuang.patchca.font.RandomFontFactory;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import com.hnq.test.generate.PatchcaConfig;
import com.hnq.toolkit.util.DateUtils;
import com.hnq.toolkit.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author henengqiang
 * @date 2018/12/3
 */
@Controller
public class PatchcaController {

    @Autowired
    private PatchcaConfig config;

    private static ConfigurableCaptchaService cs = new ConfigurableCaptchaService();

    private static Random random = new Random();

    @PostConstruct
    private void init() {
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

    private void setResponseHeaders(HttpServletResponse response) {
        response.setContentType("image/jpeg");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        long time = System.currentTimeMillis();
        response.setDateHeader("Last-Modified", time);
        response.setDateHeader("Date", time);
        response.setDateHeader("Expires", time);
    }

    @RequestMapping(value = "/defaultPatchca")
    public void getPatchcaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {

        switch (random.nextInt(5)) {
            case 0:
                cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
                break;
            case 1:
                cs.setFilterFactory(new MarbleRippleFilterFactory());
                break;
            case 2:
                cs.setFilterFactory(new DoubleRippleFilterFactory());
                break;
            case 3:
                cs.setFilterFactory(new WobbleRippleFilterFactory());
                break;
            case 4:
                cs.setFilterFactory(new DiffuseRippleFilterFactory());
                break;
            default:
                break;
        }

        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession();
        }
        setResponseHeaders(response);
        String token = EncoderHelper.getChallangeAndWriteImage(cs, "png", response.getOutputStream());
        session.setAttribute("captchaToken", token);
        System.out.println("当前的 sessionId:" + session.getId() + ",验证码：" + token);
        String time = "【调用时间：" + DateUtils.now() + "】";
        String user = "【调用者：" + HttpUtils.getIpAddress(request) + ":" + request.getRemotePort() + "】";
        System.out.println(time + user);
    }

    @RequestMapping(value = "/validateCode")
    public void validateCode(HttpServletRequest res, HttpServletResponse resp, PrintWriter out) {
        boolean flag;
        String authCode = res.getParameter("code");
        // 获取生成的验证码
        String code = (String)res.getSession().getAttribute("captchaToken");
        System.out.println(authCode + "," + code);
        flag = (code.toUpperCase()).equals(authCode.toUpperCase());
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/plain;charset=utf-8");
        // 向叶面输出数据
        try {
            out.write(String.valueOf(flag));
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getMessage")
    @ResponseBody
    public String getMessage() {
        return "I'm from picture-parchca.";
    }

}
