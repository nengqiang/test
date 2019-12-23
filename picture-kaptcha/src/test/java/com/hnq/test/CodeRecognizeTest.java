package com.hnq.test;

import com.hnq.toolkit.util.HttpUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

import static com.hnq.toolkit.util.HttpUtils.constructHeader;
import static com.hnq.toolkit.util.HttpUtils.getHttpClient;

/**
 * @author henengqiang
 * @date 2018/11/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KaptchaCodeRecognizeApplication.class)
public class CodeRecognizeTest {

    /**
     * 固定session，session值由HttpClientUtil.getSessionId()生成
     */
    private static final String SESSION_ID = "02757372b9ea4111a592afe635f22fe1";

    private static final String API_URL_FILE = "http://localhost:6000/v_code";

    private static final String API_URL_BASE64 = "http://localhost:6000/v_code_base64";

    private static final String IMG_PATH =
        "/Users/hanif/studyProjects/test/picture-recognition/src/main/resources/images/0153_1543390751768084.png";

    @Test
    public void apiTest() throws IOException {
        CookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpClient = getHttpClient(cookieStore);
        HttpPost post = new HttpPost(API_URL_BASE64);

        // 构造消息头
        constructHeader(post, SESSION_ID);

        // 构建消息实体
//        constructFileEntity(post);
//        constructByteArrayEntity(post);
//        constructStreamEntity(post);
        constructBase64StrEntity(post);

        // 执行 POST 请求
        HttpResponse res = httpClient.execute(post);
        System.out.println(res);

        // 处理获取到的原始数据流
        ByteArrayOutputStream bos = HttpUtils.getByteArrayOutputStream(res);
        System.out.println("------------------------------");
        System.out.println(bos);
    }

    /** 消息体为文件 */
    private void constructFileEntity(HttpPost post) {
        File file = new File(IMG_PATH);
        FileEntity entity = new FileEntity(file);
        post.setEntity(entity);
    }

    /** 消息体为byte数组 */
    private void constructByteArrayEntity(HttpPost post) throws IOException {
        byte[] bytes = FileUtils.readFileToByteArray(new File(IMG_PATH));
        ByteArrayEntity entity = new ByteArrayEntity(bytes);
        post.setEntity(entity);
    }

    /** 消息体为InputStream */
    private void constructStreamEntity(HttpPost post) throws FileNotFoundException {
        File file = new File(IMG_PATH);
        FileInputStream fis = new FileInputStream(file);
        InputStreamEntity entity = new InputStreamEntity(fis);
        post.setEntity(entity);
    }

    /** 消息体为Base64字符串 */
    private void constructBase64StrEntity(HttpPost post) throws UnsupportedEncodingException {
        String data = getImgBase64Str(IMG_PATH);
        StringEntity entity = new StringEntity(data);
        post.setEntity(entity);
    }

    /**
     * 将图片转换成Base64编码
     */
    public static String getImgBase64Str(String filePath) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(filePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(Base64.encodeBase64(data));
    }

}
