package com.hnq.test.login;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.hnq.toolkit.util.JsonPathUtils;
import com.treefinance.toolkit.util.Base64Codec;
import com.treefinance.toolkit.util.RegExp;
import com.treefinance.toolkit.util.http.HttpService;
import com.treefinance.toolkit.util.http.MoreHttpRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.junit.jupiter.api.Test;

/**
 * @author henengqiang
 * @date 2019/11/26
 */
class WeiBoLogin {

    @Test
    void login() throws Exception {
        String baseUrl = "https://api.weibo.com/oauth2/authorize?client_id=3673594135&redirect_uri=https%3A%2F%2Fpassport.21cn" +
                ".com%2Fapi%2Fbind%2Fbind.do%3FserviceId%3D0%26saveLogin%3D5184000%26accountType%3Dsina%26nickNameGenType%3DautoGen%26httpsOn%3D1%26returnUrl%3Dhttps%253A%252F%252Fts.21cn.com%252Ffront%252Fwap%252FthirdLoginCallBack.do%253Furl%253Dhttps%253A%252F%252Fts.21cn.com%252F%26referUrl%3Dhttps%253A%252F%252Fts.21cn.com%252F%26display%3Djson2&response_type=code";
        String regCallback = "https%3A%2F%2Fapi.weibo.com%2F2%2Foauth2%2Fauthorize%3Fclient_id%3D3673594135%26response_type%3Dcode%26display%3Ddefault%26redirect_uri%3Dhttps%253A%252F%252Fpassport.21cn.com%252Fapi%252Fbind%252Fbind.do%253FserviceId%253D0%2526amp%253BsaveLogin%253D5184000%2526amp%253BaccountType%253Dsina%2526amp%253BnickNameGenType%253DautoGen%2526amp%253BhttpsOn%253D1%2526amp%253BreturnUrl%253Dhttps%25253A%25252F%25252Fts.21cn.com%25252Ffront%25252Fwap%25252FthirdLoginCallBack.do%25253Furl%25253Dhttps%25253A%25252F%25252Fts.21cn.com%25252F%2526amp%253BreferUrl%253Dhttps%25253A%25252F%25252Fts.21cn.com%25252F%2526amp%253Bdisplay%253Djson2%26from%3D%26with_cookie%3D";
        String redirect_uri = "https://passport.21cn.com/api/bind/bind.do?serviceId=0&saveLogin=5184000&accountType=sina&nickNameGenType=autoGen&httpsOn=1&returnUrl=https%3A%2F%2Fts.21cn.com%2Ffront%2Fwap%2FthirdLoginCallBack.do%3Furl%3Dhttps%3A%2F%2Fts.21cn.com%2F&referUrl=https%3A%2F%2Fts.21cn.com%2F&display=json2";

        String userName = "qudao@treefinance.com.cn";
        String password = "dashugfd2017";
        String UA = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.80 Safari/537.36";

        String base64Username = Base64Codec.encode(URLEncoder.encode(userName, "UTF-8").getBytes());
        System.out.println(base64Username);
        String preLoginUrl = "https://login.sina.com.cn/sso/prelogin.php?entry=openapi&callback=sinaSSOController" + ".preloginCallBack&su=" +
                URLEncoder.encode(base64Username, "UTF-8") + "&rsakt=mod&checkpin=1&client=ssologin.js(v1.4.18)&_=" + System.currentTimeMillis();

        HttpClientContext context = HttpClientContext.create();
        BasicCookieStore cookieStore = new BasicCookieStore();
        context.setCookieStore(cookieStore);
        String page = HttpService.send(MoreHttpRequest.get(preLoginUrl).setUserAgent(UA), context);
        System.out.println(page);
        String jsonStr = RegExp.group(page, "\\(([^)]+)\\)", 1);
        String pubkey = JsonPathUtils.readAsString(jsonStr, "$.pubkey");
        String nonce = JsonPathUtils.readAsString(jsonStr, "$.nonce");
        String rsakv = JsonPathUtils.readAsString(jsonStr, "$.rsakv");
        String servertime = JsonPathUtils.readAsString(jsonStr, "$.servertime");
        String pcid = JsonPathUtils.readAsString(jsonStr, "$.pcid");

        String encrypt = encrypt(password, servertime, nonce, pubkey);

        String loginUrl = "https://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.4.18)&_="+System.currentTimeMillis()+"&openapilogin=qrcode";
        Map<String,Object> params = new HashMap<>();
        params.put("entry","openapi");
        params.put("gateway","1");
        params.put("savestate","0");
        params.put("useticket","1");
        params.put("ct","1800");
        params.put("s","1");
        params.put("vsnf","1");
        params.put("appkey","5Vf4ZV");
        params.put("su",base64Username);
        params.put("service","miniblog");
        params.put("servertime",servertime);
        params.put("nonce",nonce);
        params.put("pwencode","rsa2");
        params.put("rsakv",rsakv);
        params.put("sp",encrypt);
        params.put("sr","1280*800");
        params.put("encoding","UTF-8");
        params.put("cdult","2");
        params.put("domain","weibo.com");
        params.put("prelt","186");
        params.put("returntype","TEXT");

        page = HttpService.send(MoreHttpRequest.post(loginUrl).setEntity(params).setUserAgent(UA), context);
        System.out.println(page);

        String ticket = JsonPathUtils.readAsString(page,"$.ticket");
        String redirectUrl = "https://api.weibo.com/oauth2/authorize";
        Map<String,Object> params2 = new HashMap<>();
        params2.put("action","login");
        params2.put("display","default");
        params2.put("withOfficalFlag","0");
        params2.put("quick_auth","false");
        params2.put("ticket",ticket);
        params2.put("response_type","code");
        params2.put("verifyToken","null");
        params2.put("regCallback",regCallback);
        params2.put("redirect_uri",redirect_uri);
        params2.put("client_id","3673594135");
        params2.put("appkey62","5Vf4ZV");
        params2.put("switchLogin","0");

        page = HttpService.send(MoreHttpRequest.post(redirectUrl).setReferer(baseUrl).setEntity(params2).setUserAgent(UA), context);
        System.out.println(page);

        //需访问该请求获取聚投诉sessionid
        redirectUrl = "https://ts.21cn.com/front/wap/thirdLoginCallBack.do?url=https://ts.21cn.com/";
        page = HttpService.send(MoreHttpRequest.get(redirectUrl).setReferer(redirect_uri).setUserAgent(UA), context);

        //check
//        String checkUrl = "https://ts.21cn.com/front/api/post/getPostContent.do?postKey=5e41db52db2beda6baf7584c";
//        String referer = "https://ts.21cn.com/tousu/show/id/1485473";
//        page = HttpService.send(MoreHttpRequest.get(checkUrl).setUserAgent(UA).setReferer(referer), context);
//        String phone = JsonPathUtils.readAsString(page,"$.post.contactway");
//        System.out.println(phone);
        String checkUrl = "https://tousu.sina.com.cn/complaint/view/17348080977/";
        page = HttpService.send(MoreHttpRequest.get(checkUrl), context);
        System.out.println(page);

    }

    @Test
    void testEncrypt() throws Exception {
        String password = "dashugfd2017";
        String servertime = "1574758480";
        String nonce = "QNUYH3";
        String pubkey = "EB2A38568661887FA180BDDB5CABD5F21C7BFD59C090CB2D245A87AC253062882729293E5506350508E7F9AA3BB77F4333231490F915F6D63C55FE2F08A49B353F444AD3993CACC02DB784ABBB8E42A9B1BBFFFB38BE18D78E87A0E41B9B8F73A928EE0CCEE1F6739884B9777E4FE9E88A1BBE495927AC4A799B3181D6442443";
        String encrypt = encrypt(password, servertime, nonce, pubkey);
        System.out.println(encrypt);
    }

    private static String encrypt(String password, String servertime, String nonce, String pubkey) throws Exception {
        InputStream inputStream = WeiBoLogin.class.getClassLoader().getResourceAsStream("encryptjs/weiborsa.js");
        Invocable invocable = createInvocable(inputStream, "GBK");
        return invocable.invokeFunction("encrypt", servertime, nonce, password, pubkey).toString();
    }

    private static Invocable createInvocable(InputStream inputStream, String charsetName) throws Exception {
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");
        scriptEngine.eval(new InputStreamReader(inputStream, charsetName));
        return (Invocable) scriptEngine;
    }

}
