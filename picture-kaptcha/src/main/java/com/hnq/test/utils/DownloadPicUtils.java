package com.hnq.test.utils;

import com.alibaba.fastjson.JSONObject;
import com.hnq.toolkit.util.HttpUtils;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import static com.hnq.toolkit.util.HttpUtils.getHttpClient;

/**
 * @author henengqiang
 * @date 2019/02/12
 */
public class DownloadPicUtils {

    private static final String REMOTE_URL = "https://sso.toutiao.com/refresh_captcha/?method=account_login";

    private static final String FILE_PATH = "/Users/hanif/studyProjects/test/code-images/";

    public static void main(String[] args) throws IOException {

        CookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpClient = getHttpClient(cookieStore);
        HttpPost post = new HttpPost(REMOTE_URL);

        // 执行 POST 请求
        HttpResponse res = httpClient.execute(post);
        System.out.println(res);

        // 处理获取到的原始数据流
        ByteArrayOutputStream bos = HttpUtils.getByteArrayOutputStream(res);
        System.out.println(bos);
        JSONObject jsonObject = JSONObject.parseObject(bos.toString());
        String data = jsonObject.getString("captcha");
        System.out.println(data);

        String file = FILE_PATH + File.separator + "0000.png";
        FileUtils.writeByteArrayToFile(new File(file), new BASE64Decoder().decodeBuffer(data));
    }

}
