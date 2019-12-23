package com.hnq.test.controller;

import com.hnq.toolkit.util.DateUtils;
import com.hnq.toolkit.util.HttpUtils;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author henengqiang
 * @date 2018/12/3
 */
@Controller
public class JCaptchaController {

    @Autowired
    private GenericManageableCaptchaService captchaService;

    @RequestMapping(value = "/defaultJCaptcha")
    public void getCode(HttpServletRequest httpServletRequest,
                        HttpServletResponse httpServletResponse) {

        String time = "【调用时间：" + DateUtils.now() + "】";
        String user = "【调用者：" + HttpUtils.getIpAddress(httpServletRequest) + ":" + httpServletRequest.getRemotePort() + "】";
        System.out.println(time + user);

        byte[] captchaChallengeAsJpeg;
        // 输出jpg的字节流
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            String captchaId = httpServletRequest.getSession().getId();
            BufferedImage challenge =
                    (BufferedImage) captchaService.getChallengeForID(captchaId, httpServletRequest.getLocale());

            // a jpeg encoder
            JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(jpegOutputStream);
            jpegEncoder.encode(challenge);
            captchaChallengeAsJpeg = jpegOutputStream.toByteArray();

            // flush it in the response
            httpServletResponse.setHeader("Cache-Control", "no-store");
            httpServletResponse.setHeader("Pragma", "no-cache");
            httpServletResponse.setDateHeader("Expires", 0);
            httpServletResponse.setContentType("image/jpeg");
            ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
            responseOutputStream.write(captchaChallengeAsJpeg);
            responseOutputStream.flush();
            responseOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
