package com.hnq.test.login;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.treefinance.toolkit.util.http.HttpService;
import com.treefinance.toolkit.util.http.MoreHttpRequest;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.junit.jupiter.api.Test;
import org.mortbay.util.MultiMap;
import org.mortbay.util.UrlEncoded;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import javax.script.Invocable;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author henengqiang
 * @date 2019/04/22
 */
public class Email139Test {

    private String userName = "18368048354";

    private String password = "";

    private String passwordPrefix = "fetion.com.cn:";

    private CommonMethods commonMethods;

    public Email139Test() {
        commonMethods = new CommonMethods();
    }

    public static void main(String[] args) throws InterruptedException {
//        new Email139Test().wapLoginTest();
        new Email139Test().webLoginTest();
    }

    @Test
    public void requestLoginTest() {
        try {
            BasicCookieStore cookieStore = new BasicCookieStore();
            HttpClientContext context = HttpClientContext.create();
            context.setCookieStore(cookieStore);

            String referer = "http://html5.mail.10086.cn/";
            // 获取cookie
            String url = "https://html5.mail.10086.cn/mw2/weather/weather?func=user:logBehaviorAction&cguid=";
            HttpService.send(MoreHttpRequest.post(url + getCguid()).setReferer(referer), context);

            String templateUrl = "https://mail.10086.cn/Login/Login.ashx?f=1&w=1&c=1&face=B&selStyle=4&_lv=0.2&_fv=66&sidtype=mail&atl=1&authType=2&loginFailureUrl=%s&loginSuccessUrl=%s&clientid=1801";
            // 把里面的encode url单独拿出来防止format()方法遇见"s%"会报错
            String loginFailUrl = "http%3A%2F%2Fhtml5.mail.10086.cn%2F";
            String loginSuccessUrl = "http%3A%2F%2Fhtml5.mail.10086.cn%2Fhtml%2FmailList.html%3Fsource%3D1";
            url = String.format(templateUrl, loginFailUrl, loginSuccessUrl);

            Map<String, Object> data = Maps.newHashMap();
            data.put("auto", 1);
            data.put("userName", userName);
            data.put("Password", encryptPassword(passwordPrefix + password));

            HttpService.send(MoreHttpRequest.post(url)
                    .setReferer(referer)
                    .setCookies("JSESSIONID=6ABEE6C86A1EAC6D9087C11D799B0973")
                    .setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1")
                    .setEntity(JSON.toJSONString(data)), context);
            String pageContent = HttpService.send(MoreHttpRequest.get(context.getRedirectLocations().get(0).toString())
                    .setCookies("a_l=1571810243000|2145414211; a_l2=1571810243000|12|MTM3MzA4MDI5NzJ8MjAxOS0xMC0yMyAxMzo1NzoyM3x4dm1ybzQwWUlma1BUbjdkV0dIT1dBckVBYm93K3NlQURFUHU4d1F6b1hJZGc2aWJ3c0NsdURpUWd1MlhSaVFualY3SFM5L2pkMXBNZDNSSjY5NytyQT09fGUxMjU3MmZiMmZmZGEyODcxOWM4YTVlMWFlMjIyMmYz; RMKEY=0cb53df8ea83c5ad; Os_SSo_Sid=00U1NjI1ODI0MjAwMTM3OTA100980DE2000006; cookiepartid5048=12; cookiepartid=12; Login_UserNumber=13730802972; html5SkinPath5048=; provCode5048=26; areaCode5048=2603")
                    .setReferer(referer));
            System.err.println(context.getRedirectLocations());
            System.err.println("response: " + pageContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void wapLoginTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", commonMethods.getResourceFilePath("chromedriver"));
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.addArguments("--proxy-server=http://221.229.252.98:9797");
        // 驱动chrome以手机模拟器方式打开wap页面
        Map<String, String> mobileEmulation = Maps.newHashMap();
        mobileEmulation.put("deviceName", "iPhone 6 Plus");
        options.setExperimentalOption("mobileEmulation", mobileEmulation);
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://html5.mail.10086.cn/");
            WebDriverWait wait = new WebDriverWait(driver, 10);
            driver.findElement(By.id("txtUserName")).sendKeys(userName);
            driver.findElement(By.id("txtPassword")).sendKeys(password);
            driver.findElement(By.id("btnDoLogin")).click();

            // 等待验证码的出现
//            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("operationHints-default")));
            while (true) {
                WebElement element = driver.findElement(By.id("imgCode"));
                WebElement proElement = driver.findElement(By.className("operationHints-default"));
                System.err.println(proElement.getText());
                commonMethods.replaceSrcAndPrompt("139PicTemplate.html", element.getAttribute("src"), proElement.getText());
                Actions actions = new Actions(driver);

                Scanner in = new Scanner(System.in);
                System.out.println("请输入坐标：");
                String input = in.nextLine();
                String[] points = input.split(",");
                int x1 = Integer.valueOf(points[0]);
                int y1 = Integer.valueOf(points[1]);
                int x2 = Integer.valueOf(points[2]);
                int y2 = Integer.valueOf(points[3]);
                int x3 = Integer.valueOf(points[4]);
                int y3 = Integer.valueOf(points[5]);
                StopWatch watch = new StopWatch();
                watch.start();
                // 模拟鼠标移动和点击动作
                actions.moveToElement(element, 0, 0).perform();
                commonMethods.move(actions, 0, 0, x1, y1);
                commonMethods.move(actions, x1, y1, x2, y2);
                commonMethods.move(actions, x2, y2, x3, y3);
                watch.stop();
                System.err.println("模拟验证码验证耗时（仅验证过程）：" + (watch.getTime()));
                Thread.sleep(3000);
            }

//            driver.findElement(By.id("btnDoLogin")).click();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 不关driver 会留下一坨占用端口的进程
            Thread.sleep(10000);
            driver.quit();
            System.err.println(">>> Driver has closed");
        }
    }

    @Test
    public void webLoginTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", commonMethods.getResourceFilePath("chromedriver"));
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
//        options.addArguments("--proxy-server=http://117.141.155.242:53281");
        // 江苏
        options.addArguments("--proxy-server=http://221.229.252.98:9797");
        Map<String, Set<Cookie>> paramMap = webLoginTestGetPic(options);
        webLoginTestToLogin(options, Objects.requireNonNull(paramMap));
    }

    private Map<String, Set<Cookie>> webLoginTestGetPic(ChromeOptions options) {
        StopWatch watch = new StopWatch();
        watch.start();
        String prompt = null;
        Map<String, Set<Cookie>> map = Maps.newHashMap();
        WebDriver driver = new ChromeDriver(options);
        // 设置浏览器大小以方便截图
        driver.manage().window().setSize(new Dimension(1200, 800));
        try {
            driver.get("https://html5.mail.10086.cn/");
            driver.findElement(By.linkText("电脑版")).click();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            driver.findElement(By.id("txtUser")).sendKeys(userName);
            driver.findElement(By.id("txtPass")).sendKeys(password);
            driver.findElement(By.id("loginBtn")).click();

            // 等待验证码的出现
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("imageVerificationCode")));
            WebElement element = driver.findElement(By.id("imageVerificationCode"));
            WebElement proElement = driver.findElement(By.className("operationHints-default"));
            prompt = proElement.getText();

            File codeImage = new File("/Users/hanif/studyProjects/test/crawler/src/test/resources/codepics/139code.png");
            File windows = new File("/Users/hanif/studyProjects/test/crawler/src/test/resources/codepics/windows.png");
//                String screenshot = element.getScreenshotAs(OutputType.BASE64);
//                byte[] screenshot = element.getScreenshotAs(OutputType.BYTES);
//                FileUtils.writeByteArrayToFile(codeImage, screenshot);

            ImageIO.write(takeScreenshot(driver), "png", windows);
            ImageIO.write(createElementImage(driver, element), "png", codeImage);

            commonMethods.replaceSrcAndPrompt("139PicTemplate.html", "/Users/hanif/studyProjects/test/crawler/src/test/resources/codepics/139code.png", proElement.getText());
            String validUrl = driver.getCurrentUrl();
            Set<Cookie> cookies = driver.manage().getCookies();

            map.put(validUrl, cookies);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            watch.stop();
            driver.quit();
            System.err.println(prompt);
            System.err.println(">>> Get Pic Driver has closed. Cost " + watch.getTime() / 1000.0 + "s");
        }
        return map;
    }

    private void webLoginTestToLogin(ChromeOptions options, Map<String, Set<Cookie>> param) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        String validUrl = "";
        Set<Cookie> cookies = Sets.newHashSet();
        for (Map.Entry<String, Set<Cookie>> stringSetEntry : param.entrySet()) {
            validUrl = stringSetEntry.getKey();
            cookies = stringSetEntry.getValue();
        }
        String prompt1 = null, prompt2 = null, prompt3 = null;
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get(validUrl);
            cookies.forEach(cookie -> driver.manage().addCookie(cookie));
            WebElement element = driver.findElement(By.id("imageVerificationCode"));
            WebDriverWait wait = new WebDriverWait(driver, 10);

            Actions actions = new Actions(driver);

            watch.split();
            watch.suspend();
            long step1Time = watch.getSplitTime();
            prompt1 = ">>> Login step1 cost " + step1Time / 1000.0 + "s";

            Scanner in = new Scanner(System.in);
            System.out.println("请输入坐标：");
            String input = in.nextLine();
            String[] points = input.split(",");
            int x1 = Integer.valueOf(points[0]);
            int y1 = Integer.valueOf(points[1]);
            int x2 = Integer.valueOf(points[2]);
            int y2 = Integer.valueOf(points[3]);
            int x3 = Integer.valueOf(points[4]);
            int y3 = Integer.valueOf(points[5]);

            watch.resume();
            // 模拟鼠标移动和点击动作
            actions.moveToElement(element, 0, 0).perform();
            commonMethods.move(actions, 0, 0, x1, y1);
            commonMethods.move(actions, x1, y1, x2, y2);
            commonMethods.move(actions, x2, y2, x3, y3);
            watch.split();
            prompt2 = ("模拟验证码验证耗时（仅验证过程）：" + (watch.getSplitTime() - step1Time) / 1000.0 + "s");
//            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@class='operationHints']"), "验证成功"));
            String codeUrl = driver.getCurrentUrl();
            driver.findElement(By.id("btnLogin")).click();
//            wait.until((ExpectedCondition<Boolean>) d -> (d != null ? d.getCurrentUrl().length() : 500) > codeUrl.length());
            String url = driver.getCurrentUrl();
            MultiMap paramMap = new MultiMap();
            UrlEncoded.decodeTo(url.substring(url.indexOf("?") + 1), paramMap, "UTF-8");
            String templateUrl = "https://html5.mail.10086.cn/html/mailList.html?source=1&sid=%s&rnd=%s&comefrom=%s&useInnerWeb=0&k=%s&ishttps=1&fid=1&clientid=1801&cguid=%s&mtime=%s";
            url = String.format(templateUrl, paramMap.get("sid"), paramMap.get("rnd"), paramMap.get("comefrom"), paramMap.get("k"), paramMap.get("cguid"), paramMap.get("mtime"));
            driver.get(url);
            watch.stop();
            prompt3 = (">>> Login success. Cost " + watch.getTime() / 1000.0 + "s");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 不关driver 会留下一坨占用端口的进程
            System.err.println(prompt1);
            System.err.println(prompt2);
            System.err.println(prompt3);
            Thread.sleep(10000);
            driver.quit();
            System.err.println(">>> Driver has closed");
        }
    }

    @Test
    public void webLoginTest2() throws Exception {
        System.setProperty("webdriver.chrome.driver", commonMethods.getResourceFilePath("chromedriver"));
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://html5.mail.10086.cn/");
        driver.findElement(By.linkText("电脑版")).click();
        driver.findElement(By.id("txtUser")).sendKeys(userName);
        driver.findElement(By.id("txtPass")).sendKeys(password);
        driver.findElement(By.id("loginBtn")).click();
//        driver.get("https://html5.mail.10086.cn/");
        Thread.sleep(2000);
        String url = driver.getCurrentUrl();
        MultiMap paramMap = new MultiMap();
        UrlEncoded.decodeTo(url.substring(url.indexOf("?") + 1), paramMap, "UTF-8");
        String templateUrl = "https://html5.mail.10086.cn/html/mailList.html?source=1&sid=%s&rnd=%s&comefrom=%s&useInnerWeb=0&k=%s&ishttps=1&fid=1&clientid=1801&cguid=%s&mtime=%s";
        url = String.format(templateUrl, paramMap.get("sid"), paramMap.get("rnd"), paramMap.get("comefrom"), paramMap.get("k"), paramMap.get("cguid"), paramMap.get("mtime"));
        driver.get(url);
        Thread.sleep(5000);
        driver.quit();
    }

    class MyWebDriver extends ChromeDriver {

        public MyWebDriver(ChromeOptions options) {
            super(options);
        }

        @Override
        public void setSessionId(String opaqueKey) {
            super.setSessionId(opaqueKey);
        }
    }

    @Test
    public void encryptTest() throws Exception {
        System.out.println(encryptPassword(passwordPrefix + "123"));
        System.out.println();
    }

    @Test
    public void cookieTest() throws Exception {
        System.setProperty("webdriver.chrome.driver", commonMethods.getResourceFilePath("chromedriver"));
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        WebDriver driver = new ChromeDriver(options);
        String url = "https://html5.mail.10086.cn/mw2/weather/weather?func=user:logBehaviorAction&cguid=";
//        driver.get(url + getCguid());
        driver.get("https://html5.mail.10086.cn/");
        Set<Cookie> cookie = driver.manage().getCookies();
        //获取cookie
        System.err.println(cookie);
        //打印cookie
        driver.quit();
    }


    private String encryptPassword(String password) throws Exception {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("encryptjs/email139.js");
        Invocable invocable = commonMethods.createInvocable(inputStream, "GBK");
        return invocable.invokeFunction("doSha1", password).toString();
    }

    private String getCguid() throws Exception {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("encryptjs/email139.js");
        Invocable invocable = commonMethods.createInvocable(inputStream, "GBK");
        return invocable.invokeFunction("getCguid").toString();
    }

    /**
     * WebDriver截屏方法
     */
    public static BufferedImage takeScreenshot(WebDriver driver) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        ByteArrayInputStream bis =  new ByteArrayInputStream(takesScreenshot.getScreenshotAs(OutputType.BYTES));
        return ImageIO.read(bis);
    }

    /**
     * 根据节点位置，对节点进行裁剪，获得截图
     */
    public static BufferedImage createElementImage(WebDriver driver, WebElement webElement) throws IOException {
        // 获得webElement的位置和大小。
        Point location = webElement.getLocation();
        Dimension size = webElement.getSize();
        // 创建全屏截图。
        BufferedImage originalImage = takeScreenshot(driver);
        // 截取webElement所在位置的子图。（乘以2是因为截的全图扩大了2倍，暂不知道怎么回事）
        return originalImage.getSubimage(location.getX() * 2, location.getY() * 2, size.getWidth() * 2, size.getHeight() * 2);
    }
}
