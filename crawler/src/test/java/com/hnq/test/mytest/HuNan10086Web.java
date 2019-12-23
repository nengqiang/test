package com.hnq.test.mytest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hnq.test.common.CrawlUtils;
import com.hnq.toolkit.util.FileUtils;
import com.treefinance.crawler.framework.util.xpath.XPathUtil;
import com.treefinance.toolkit.util.http.HttpService;
import com.treefinance.toolkit.util.http.MoreHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.script.Invocable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author henengqiang
 * @date 2019/06/19
 */
@Slf4j
public class HuNan10086Web {

//    private static final String MOBILE = "13574690021";
    private static final String MOBILE = "15197255505";

//    private static final String PASSWORD = "332211";
    private static final String PASSWORD = "155505";

    private static final String WEBSITE_NAME = "hu_nan_10086_wap";

    private static Scanner sc = new Scanner(System.in);

    @Test
    public void submitForLogin() {

        String smsCode = "13574690021";

        String response = "";
        try {
            BasicCookieStore cookieStore = new BasicCookieStore();
            HttpClientContext context = HttpClientContext.create();
            context.setCookieStore(cookieStore);

            Invocable invocable = CrawlUtils.createInvocable( "hunan10086web/des.js", "GBK");
            String encryptPassword = invocable.invokeFunction("strEnc", PASSWORD, MOBILE.substring(0, 8), MOBILE.substring(1, 9), MOBILE.substring(3, 11)).toString();

            BigDecimal db = new BigDecimal(Math.random() * (1) + 0);
            String referer = "http://www.hn.10086.cn/newservice/static/componant/login.html";
            String templateUrl = "http://www.hn.10086.cn/service/ics/login/SSOLogin?REMEMBER_TAG=false&SERIAL_NUMBER=%s&LOGIN_TYPE=2&USER_PASSWD" +
                    "=%s&USER_PASSSMS=%s&VALIDATE_CODE=&chanId=E003&operType=LOGIN&goodsName=%s&loginType=0&ajaxSubmitType=post&ajax_randomcode=%s";
            //获取tokenId并追加到cookie
            String tokenId = db.setScale(17, BigDecimal.ROUND_HALF_UP).toString();

            String url = String.format(templateUrl, MOBILE, encryptPassword, smsCode, "%E6%9C%8D%E5%8A%A1%E5%AF%86%E7%A0%81%E7%99%BB%E5%BD%95", db.setScale(16, BigDecimal.ROUND_HALF_UP));

            response = HttpService.send(MoreHttpRequest.post(url).setReferer(referer));
            JSONObject json = JSON.parseObject(response);
            String result = json.getString("RESULT");
            String resultInfo = json.getString("RESULTINFO");
            if (StringUtils.equals(resultInfo, "登陆成功") || StringUtils.equals(result, "0")) {
                log.info("登陆成功,result={}", result);
            } else {
                log.error("登陆失败,result={},response={}", result, response);
            }
        } catch (Exception e) {
            log.error("登陆失败,response={}", response, e);
        }
    }

    @Test
    public void encryptPwdTest() {
        try {
            Invocable invocable = CrawlUtils.createInvocable( "hunan10086web/des.js", "GBK");
            String encryptPassword = invocable.invokeFunction("strEnc", PASSWORD, MOBILE.substring(0, 8), MOBILE.substring(1, 9), MOBILE.substring(3, 11)).toString();
            System.out.println(encryptPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void submitForLoginFromWebBrowser() {
        String url = "http://wap.hn.10086.cn/wap/static/login/Login.html";
//        String url = "https://login.10086.cn/login.html";
//        String url = "http://www.hn.10086.cn/newservice/static/componant/login.html";
        System.setProperty("webdriver.chrome.driver", FileUtils.getResourceFilePath(RequestTest.class,"chromedriver"));
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        String proxy = CrawlUtils.getProxyStr();
        options.addArguments("--proxy-server=http://" + proxy);
        WebDriver driver = new ChromeDriver(options);
        String pageContent = null;
        try {
            driver.get(url);

            /*WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(d -> d.findElement(By.id("sms_login_1")));
            driver.findElement(By.id("sms_login_1")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sms_name")));
            driver.findElement(By.id("sms_name")).sendKeys(MOBILE);
            driver.findElement(By.id("getSMSPwd1")).click();

            String smsCode = sc.nextLine();
            driver.findElement(By.id("sms_pwd_l")).sendKeys(smsCode);

            driver.findElement(By.id("submit_bt")).click();

            // 等待网站验证短信验证码然后得出结果，然后再获取它的这个结果进行判断
            Thread.sleep(300);
            pageContent = driver.getPageSource();

            List<String> msgList = XPathUtil.getXpath("//div[@id='smspwd_err']/text()", pageContent);
            log.debug(String.valueOf(msgList));
            String smsMsg = CollectionUtils.isNotEmpty(msgList) ? msgList.get(0) : null;
            if (StringUtils.containsAny(smsMsg, "输入正确短信", "不正确", "过期", "重新")) {
                log.error("登陆失败，errMsg={}， pageContent={}", smsMsg, pageContent);
            } else {
                log.info("短信验证码登录成功！");
            }

            Thread.sleep(5);*/

            /*driver.findElement(By.id("logout")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dropdownMenu2")));
            driver.findElement(By.id("dropdownMenu2")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("service_login_main")));

            driver.findElement(By.id("p_name")).sendKeys(MOBILE);
            driver.findElement(By.id("p_pwd")).sendKeys(PASSWORD);
            driver.findElement(By.id("submit_bt")).click();*/


            Thread.sleep(2000000);
        } catch (Exception e) {
            log.error("登陆失败，pageContent={}", pageContent, e);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void refreshSmsCodeForLogin() {
        String url = "https://login.10086.cn/login.html";
        System.setProperty("webdriver.chrome.driver", FileUtils.getResourceFilePath(RequestTest.class,"chromedriver"));
        ChromeOptions options = new ChromeOptions();
        String proxy = CrawlUtils.getProxyStr();
        options.addArguments("--proxy-server=http://" + proxy);
        WebDriver driver = new ChromeDriver(options);
        String pageContent = null;
        try {
            driver.get(url);

            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(d -> d.findElement(By.id("sms_login_1")));
            driver.findElement(By.id("sms_login_1")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sms_name")));
            driver.findElement(By.id("sms_name")).sendKeys(MOBILE);
            driver.findElement(By.id("getSMSPwd1")).click();

            // 等待网站对获取验证码这个行为作出反应，然后它会改变页面元素，我们获取改变后的页面内容进行判断
            Thread.sleep(200);
            pageContent = driver.getPageSource();
            List<String> msgList = XPathUtil.getXpath("//div[@id='msmsendtips']/text()", pageContent);
            String smsMsg = CollectionUtils.isNotEmpty(msgList) ? msgList.get(0) : null;
            if (StringUtils.containsAny(smsMsg, "对不起", "达到上限", "发送失败", "下发失败")) {
                log.error("随机短信验证码下发失败，errMsg={}, pageContent={}", smsMsg, pageContent);
            } else {
                log.info("成功！");
            }
            Thread.sleep(30000);
        } catch (Exception e) {
            log.error("随机短信验证码下发失败，pageContent={}", pageContent, e);
        } finally {
            driver.quit();
        }
    }

    public static void main(String[] args) {
        new HuNan10086Web().submitForLoginFromWebBrowser();
    }

}
