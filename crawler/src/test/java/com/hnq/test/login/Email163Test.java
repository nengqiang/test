package com.hnq.test.login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.treefinance.toolkit.util.http.HttpService;
import com.treefinance.toolkit.util.http.MoreHttpRequest;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.script.Invocable;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * @author henengqiang
 * @date 2019/04/09
 */
public class Email163Test {

    private CommonMethods commonMethods;

    public Email163Test() {
        commonMethods = new CommonMethods();
    }

    /** ---------- *
     *             *
     *     MAIN    *
     *             *
     * ----------- */
    public static void main(String[] args) throws Exception {
        new Email163Test().webLoginTest();
    }

    @Test
    public void requestLoginTest() {
        try {
            String account  = "jane1229@163.com";
            String password = "123123@abc";
            String rtid = getRtid();
            BasicCookieStore cookieStore = new BasicCookieStore();
            // 生成的Cookie
            BasicClientCookie cookie = new BasicClientCookie("JSESSIONID-WYTXZDL", getJSESSIONID());
            cookie.setDomain("dl.reg.163.com");
            cookieStore.addCookie(cookie);
            HttpClientContext context = HttpClientContext.create();
            context.setCookieStore(cookieStore);

            // 获取Cookie
            String url = String.format("https://dl.reg.163.com/dl/ini?pkht=reg.163.com&pdconf=yddl_mail163_conf&re=1&pd=urs&pkid=ivkxhkV&opd=mail163&rtid=%s", rtid);
            HttpService.send(MoreHttpRequest.get(url), context);

            // 获取tk
            String basicRequest1 = "https://dl.reg.163.com/dl/gt?un=%s&pd=urs&pkid=ivkxhkV&opd=mail163&rtid=%s";
            String request1 = String.format(basicRequest1, URLEncoder.encode(account, "UTF-8"), rtid);
            String pageContent = HttpService.send(MoreHttpRequest.get(request1), context);
            JSONObject jsonObject = JSONObject.parseObject(pageContent);
            String tk = jsonObject.getString("tk");

            // 登录
            String referer = "https://dl.reg.163.com/ydzj/maildl?product=mail163&pdconf=yddl_mail163_conf&mc=0F6099&curl=https://mail.163.com/entry/cgi/ntesdoor?from=smart";
            String basicRequest2 = "https://dl.reg.163.com/dl/l";
            Map<String, Object> data = Maps.newHashMap();
            data.put("d", 10);
            data.put("l", 1);
            data.put("opd", "mail163");
            data.put("pd", "urs");
            data.put("pkid", "ivkxhkV");
            data.put("pw", encryptPassword(password));
            data.put("rtid", rtid);
            data.put("tk", tk);
            data.put("un", account);

            System.err.println("requestData: " + JSON.toJSONString(data));
            System.err.println("Cookie: " + JSON.toJSONString(context.getCookieStore().getCookies()));

            pageContent = HttpService.send(MoreHttpRequest.post(basicRequest2)
                    .setReferer(referer)
                    .setEntity(JSON.toJSONString(data))
                    .setContentType(ContentType.getByMimeType("application/json")), context);
            System.err.println("response: " + pageContent);
            Assertions.assertEquals("{\"ret\":\"201\"}", pageContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void webLoginTest() throws Exception {
        System.setProperty("webdriver.chrome.driver", commonMethods.getResourceFilePath("chromedriver"));
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        //options.addArguments("--proxy-server=http://125.121.136.177:6666");
        WebDriver driver = new ChromeDriver(options);
//        WebDriver driver2 = new ChromeDriver(options);
        // 调用远程浏览器
//        driver = new RemoteWebDriver(new URL("http://121.43.180.135:6666/wd/hub"), capabilities);
        try {
            driver.get("https://dl.reg.163.com/ydzj/maildl?product=mail163&pdconf=yddl_mail163_conf&mc=0F6099&curl=https://mail.163.com/entry/cgi/ntesdoor?from=smart");
            WebDriverWait wait = new WebDriverWait(driver, 10);
            //wait.until(ExpectedConditions.jsReturnsValue("Object.defineProperties(navigator,{webdriver:{get:() => false}});return 0;"));

            //String url = (String)wait.until(ExpectedConditions.jsReturnsValue("return document.getElementById('login_frame').src"));
            //driver.get(url);
            driver.findElement(By.name("account")).sendKeys("133");
            driver.findElement(By.xpath("//input[@type='password']")).sendKeys("123");
            driver.findElement(By.xpath("//div[@class='u-btn c-main']/button")).click();

            // 等待验证按钮出现
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='yidun_tips']")));
            while (true) {
                // 点击验证按钮
                driver.findElement(By.xpath("//div[@class='yidun_tips']")).click();
                // 等待验证图片出现
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("yidun_panel")));
                // 它这个验证码图片出现还有个加载中的过程，所以也要等它加载完成
                wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("yidun_tips"), "请依次点击"));
                WebElement element = driver.findElement(By.className("yidun_bg-img"));
                WebElement proElement = driver.findElement(By.className("yidun_tips"));
                System.err.println(proElement.getText());
                commonMethods.replaceSrcAndPrompt("test.html", element.getAttribute("src"), proElement.getText());

//                String validUrl = driver.getCurrentUrl();
//                Set<Cookie> cookies = driver.manage().getCookies();
//                driver.quit();
//                Actions actions = new Actions(driver2);

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

//                driver2.get(validUrl);
//                cookies.forEach(cookie -> driver2.manage().addCookie(cookie));
//                WebDriverWait wait2 = new WebDriverWait(driver2, 10);
//                driver2.findElement(By.name("account")).sendKeys("133");
//                driver2.findElement(By.xpath("//input[@type='password']")).sendKeys("123");
//                driver2.findElement(By.xpath("//div[@class='u-btn c-main']/button")).click();

                StopWatch watch = new StopWatch();
                watch.start();

                // 等待验证按钮出现
//                wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='yidun_tips']")));
                driver.findElement(By.xpath("//div[@class='yidun_tips']")).click();
                // 等待验证图片出现
//                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("yidun_panel")));
//                element = driver2.findElement(By.className("yidun_bg-img"));

                // 模拟鼠标移动和点击动作
                // 目前只测试出移动次数为60,50,40,30能成功(>=70或<=20就会失败)，移动次数越少，验证越快
//                int stepNum = RandomUtils.nextInt(30, 60 + 1);
//                simulateMoveAction1(element, actions, 0, 0, x1, y1, stepNum);
//                simulateMoveAction1(element, actions, x1, y1, x2, y2, stepNum);
//                simulateMoveAction1(element, actions, x2, y2, x3, y3, stepNum);

//                actions.moveToElement(element, 0, 0).perform();
//                simulateMoveAction2(actions, 0, 0, x1, y1, stepNum);
//                simulateMoveAction2(actions, x1, y1, x2, y2, stepNum);
//                simulateMoveAction2(actions, x2, y2, x3, y3, stepNum);

                actions.moveToElement(element, 0, 0).perform();
                commonMethods.move(actions, 0, 0, x1, y1);
                commonMethods.move(actions, x1, y1, x2, y2);
                commonMethods.move(actions, x2, y2, x3, y3);

                watch.stop();
                System.err.println("模拟验证码验证耗时（仅验证过程）：" + (watch.getTime()));
                Thread.sleep(3000);
            }

//            driver.findElement(By.xpath("//div[@class='u-btn c-main']/button")).click();

//            System.err.println(driver.getPageSource());
        } finally {
            // 不关driver 会留下一坨占用端口的进程
            Thread.sleep(10000);
            driver.quit();
//            driver2.quit();
            System.err.println("Driver has closed");
        }
    }

    @Test
    public void encryptTest() throws Exception {
        String password = "123123@abc";
        System.out.println(encryptPassword(password));
    }

    /**
     * 点触摸验证码-模拟鼠标移动
     * @param element   网页元素对象
     * @param actions   动作对象
     * @param x1        起始点x坐标
     * @param y1        起始点y坐标
     * @param x2        终点x坐标
     * @param y2        终点y坐标
     * @param stepNum   移动次数
     */
    private void simulateMoveAction1(WebElement element, Actions actions, int x1, int y1, int x2, int y2, int stepNum) {
        int sx = Math.abs(x1 - x2);
        int sy = Math.abs(y1 - y2);
        int[] orbitX = randomSplit(sx, stepNum, sx / stepNum - 3, sx / stepNum + 3);
        int[] orbitY = randomSplit(sy, stepNum, sy / stepNum - 3, sy / stepNum + 3);
        int symbolX = x2 > x1 ? 1 : -1;
        int symbolY = y2 > y1 ? 1 : -1;

        int pointX = x1, pointY = y1;
        for (int i = 0; i < stepNum; i++) {
            int lastPointX = pointX;
            int lastPointY = pointY;
            pointX = pointX + orbitX[i] * symbolX;
            pointY = pointY + orbitY[i] * symbolY;
            pointX = pointX < 0 ? lastPointX : pointX;
            pointY = pointY < 0 ? lastPointY : pointY;
            actions.moveToElement(element, pointX, pointY).perform();
        }
        actions.click().perform();
    }

    private void simulateMoveAction2(Actions actions, int x1, int y1, int x2, int y2, int stepNum) throws InterruptedException {
        int sx = Math.abs(x1 - x2);
        int sy = Math.abs(y1 - y2);
        int[] orbitX = randomSplit(sx, stepNum, sx / stepNum - 3, sx / stepNum + 3);
        int[] orbitY = randomSplit(sy, stepNum, sy / stepNum - 3, sy / stepNum + 3);
        int symbolX = x2 > x1 ? 1 : -1;
        int symbolY = y2 > y1 ? 1 : -1;

        int pointX = x1, pointY = y1;
        int offsetX, offsetY;
        for (int i = 0; i < stepNum; i++) {
            int lastPointX = pointX;
            int lastPointY = pointY;
            offsetX = orbitX[i] * symbolX;
            offsetY = orbitY[i] * symbolY;
            pointX = pointX + offsetX;
            pointY = pointY + offsetY;
            // 防止鼠标移到图片外面去
            offsetX = pointX < 0 ? 0 : offsetX;
            offsetY = pointY < 0 ? 0 : offsetY;
            pointX = pointX < 0 ? lastPointX : pointX;
            pointY = pointY < 0 ? lastPointY : pointY;
            Thread.sleep(RandomUtils.nextInt(5, 10));
            actions.moveByOffset(offsetX, offsetY).perform();
        }
        actions.click().perform();
    }

    private int[] randomSplit(int x, int n, int min, int max) {
        int[] result = new int[n];
        min = min < 0 ? 0 : min;
        int i = 0;
        while (n > 0) {
            int l = Math.max(min, x - (n - 1) * max);
            int r = Math.min(max, x - (n - 1) * min);
            int num = RandomUtils.nextInt(l, r + 1);
            n -= 1;
            x -= num;
            result[i++] = num;
        }
        return result;
    }

    private String getJSESSIONID() throws Exception {
        WebDriver driver = null;
        try {
            System.setProperty("webdriver.chrome.driver", commonMethods.getResourceFilePath("chromedriver"));
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
            driver.get("https://dl.reg.163.com/ydzj/maildl?product=mail163&pdconf=yddl_mail163_conf&mc=0F6099&curl=https://mail.163.com/entry/cgi/ntesdoor?from=smart");
            return driver.manage().getCookieNamed("JSESSIONID-WYTXZDL").getValue();
        } finally {
            if (driver != null) {
                driver.close();
            }
        }
    }

    private String encryptPassword(String password) throws Exception {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("encryptjs/email163.js");
        Invocable invocable = commonMethods.createInvocable(inputStream, "GBK");
        return invocable.invokeFunction("encrypt2", password).toString();
    }



    private String getRtid() {
        int resultLength = 32;
        StringBuilder sb = new StringBuilder();
        String param = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random r = new Random();
        for (int i = 0; i < resultLength; i++) {
            sb.append(param.charAt(r.nextInt(param.length())));
        }
        return sb.toString();
    }

}
