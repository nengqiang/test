package com.hnq.test.controller;

import com.github.cage.Cage;
import com.hnq.test.generator.MyFontGenerator;
import com.hnq.test.generator.MyPainter;
import com.hnq.test.generator.MyTokenGenerator;
import com.hnq.toolkit.util.DateUtils;
import com.hnq.toolkit.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * @author henengqiang
 * @date 2018/12/4
 */
@Controller
public class CageController {


    @Autowired
    private MyFontGenerator myFontGenerator;

    @Autowired
    private MyTokenGenerator myTokenGenerator;

    private MyPainter myPainter = new MyPainter();

    private Cage cage = new Cage(myPainter, myFontGenerator,null,null,null, myTokenGenerator,null);

    @RequestMapping("/defaultCage")
    public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String time = "【调用时间：" + DateUtils.now()+ "】";
        String user = "【调用者：" + HttpUtils.getIpAddress(request) + ":" + request.getRemotePort() + "】";
        System.out.println(time + user);

        // 设置响应的媒体类型，这样浏览器会识别出响应的是图片
        response.setContentType("image/jpeg");
        response.getOutputStream().write(Objects.requireNonNull(getCaptcha()));
        response.flushBuffer();
    }

    private byte[] getCaptcha() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        String token = cage.getTokenGenerator().next().substring(0, 4);
        try {
            cage.draw(token, os);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return os.toByteArray();
    }

}
