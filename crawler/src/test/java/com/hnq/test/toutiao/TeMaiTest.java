package com.hnq.test.toutiao;

import com.alibaba.fastjson.JSONObject;
import com.treefinance.toolkit.util.http.HttpService;
import com.treefinance.toolkit.util.http.MoreHttpRequest;
import com.treefinance.toolkit.util.http.exception.HttpException;
import org.junit.jupiter.api.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author henengqiang
 * @date 2019/07/05
 */
public class TeMaiTest {

//    private static final String SHOP_ID = "xvSXIzz";
    private static final String SHOP_ID = "dSgYTqu";

    @Test
    public void index() throws HttpException {
        /**
         *  可查店铺评分
         */
        String url = "https://haohuo.snssdk.com/shop/getdsr?id=" + SHOP_ID;
        String page = HttpService.get(url);
        System.out.println(page);
    }

    @Test
    public void goods() throws Exception {
        /**
         * 获取商品列表，每页最多20条，翻页至无数据的页面可停止翻页
         */
        String url = "https://haohuo.snssdk.com/productcategory/getShopList?shop_id=" + SHOP_ID + "&type=5&sort=1&page=0&size=20";
        String page = HttpService.get(url);
        System.out.println(page);
    }

    @Test
    public void zhizhao() throws Exception {
        /**
         * 获取店铺执照图片地址
         */
        String url = "https://haohuo.snssdk.com/shop/getBusList?id=" + SHOP_ID;
        String page = HttpService.get(url);
        System.out.println(page);
        JSONObject object = JSONObject.parseObject(page);
        String imgUrl = (String) ((JSONObject) object.getJSONArray("data").get(0)).getJSONArray("imgs").get(0);
        System.out.println(imgUrl);
    }

    @Test
    public void detail() throws Exception {
        /**
         * 获取商品信息、店铺的mobile也在这里
         */
        String id = "3348516958093076202";
        String url = "https://ec.snssdk.com/product/fxgajaxstaticitem?id=" + id + "&origin_type=0&token=" + getToken(id);
        String page = HttpService.send(MoreHttpRequest.get(url).setReferer("https://haohuo.jinritemai.com/views/product/item"));
        System.out.println(page);
    }

    @Test
    public void extra() throws Exception {
        /**
         * 获取商品关注数、销量、关注人数等
         */
        String id = "3348516958093076202";
        String url = "https://ec.snssdk.com/product/ajaxitem?id=" + id + "&origin_type=0&token=" + getToken(id);
        String page = HttpService.send(MoreHttpRequest.get(url).setReferer("https://haohuo.jinritemai.com/views/product/item"));
        System.out.println(page);
    }

    @Test
    public void pingjia() throws Exception {
        /**
         * 获取评价数量、标签
         */
        String id = "3348516958093076202";
        String url = "https://ec.snssdk.com/comment/itemList?b_type_new=0&id=" + id + "&contentType=json";
        String page = HttpService.send(MoreHttpRequest.get(url));
        System.out.println(page);
    }

    @Test
    public void pingjiaDetail() throws Exception {
        /**
         * 获取全部评价、每页10条
         */
        String id = "3348516958093076202";
        String url = "https://ec.snssdk.com/comment/listajax?b_type_new=0&product_id=" + id + "&page=1&type=0&size=10";
        String page = HttpService.send(MoreHttpRequest.get(url));
        System.out.println(page);
    }

    public static String getToken(String str) throws Exception {
        InputStream inputStream = TeMaiTest.class.getClassLoader().getResourceAsStream("toutiao/token.js");
        Invocable invocable = createInvocable(inputStream, "GBK");
        String encrypt = invocable.invokeFunction("getToken", str).toString();
        return encrypt;
    }

    private static Invocable createInvocable(InputStream inputStream, String charsetName) throws Exception {
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");
        scriptEngine.eval(new InputStreamReader(inputStream, charsetName));
        return (Invocable) scriptEngine;
    }

    @Test
    public void requestTest() {
        String url = "https://ec.snssdk.com/product/fxgajaxstaticitem?id=3306844258132223669&token=ddfa175518ea8259c2d6a40ce68d5df4&b_type_new=0";
        String referer = "https://haohuo.jinritemai.com/views/product/item";
        String result = null;
        try {
            result = HttpService.send(MoreHttpRequest.get(url));
        } catch (HttpException e) {
            e.printStackTrace();
        }
        System.err.println(result);
        try {
            result = HttpService.send(MoreHttpRequest.get(url).setReferer(referer).setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36"));
        } catch (HttpException e) {
            e.printStackTrace();
        }
        System.err.println("result=" + result);
    }

    @Test
    public void test() {
//        String phone = "   08334262546         08334262406";
//        String phone = "   08334262546         083-34262406";
        String phone = "083-34262546";
//        String phone = "  4008-789-039  ";
//        String phone = "";
//        String phone = null;
        List<String> phones = Arrays.stream(phone.split("\\s+")).filter(e -> e.matches("[\\d-]+")).collect(Collectors.toList());
        System.out.println(phones);
        System.out.println(phones.get(0));
        System.out.println(phone.matches("[\\d-]+"));
    }
}
