package com.hnq.test.login;

import com.treefinance.toolkit.util.http.HttpService;
import com.treefinance.toolkit.util.http.MoreHttpRequest;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author henengqiang
 * @date 2019/04/09
 */
public class EmailQQTest {

    private static final Logger logger  = LoggerFactory.getLogger(EmailQQTest.class);

    private static Pattern pattern = Pattern.compile("\\\\x([^\\\\]+)");

    @Test
    public void requestLoginTest() {
        try {
            String account = "jane29@qq.com";
            String password = "455409461#ABC";

            BasicCookieStore cookieStore = new BasicCookieStore();
            HttpClientContext context = HttpClientContext.create();
            context.setCookieStore(cookieStore);

            // 获取Cookie 和参数
            String url = "https://ui.ptlogin2.qq.com/cgi-bin/login?style=9&appid=522005705&daid=4&s_url=https%3A%2F%2Fw.mail.qq.com%2Fcgi-bin%2Flogin%3Fvt%3Dpassport%26vm%3Dwsk%26delegate_url%3D%26f%3Dxhtml%26target%3D&hln_css=http%3A%2F%2Fmail.qq.com%2Fzh_CN%2Fhtmledition%2Fimages%2Flogo%2Fqqmail%2Fqqmail_logo_default_200h.png&low_login=1";
            String pageContent = HttpService.send(MoreHttpRequest.get(url), context);
            Pattern appidP = Pattern.compile("ptui_appid=\\w+\\(\"(\\d+)\"\\)");
            Pattern ptlangP = Pattern.compile("ptui_lang=\\w+\\(\"(\\d+)\"\\)");
            Pattern ptuistyleP = Pattern.compile("ptui_style=\\w+\\(\"(\\d+)\"\\)");

            String appid = "", ptlang = "", ptuistyle = "";
            Matcher matcher1 = appidP.matcher(pageContent);
            if (matcher1.find()) {
                appid = matcher1.group(1);
            }
            Matcher matcher2 = ptlangP.matcher(pageContent);
            if (matcher2.find()) {
                ptlang = matcher2.group(1);
            }
            Matcher matcher3 = ptuistyleP.matcher(pageContent);
            if (matcher3.find()) {
                ptuistyle = matcher3.group(1);
            }

            // 获取 pt_verifysession_v1 等参数
            String templateUrl = "https://ssl.ptlogin2.qq.com/check?pt_tea=2&uin=%s&appid=%s&ptlang=%s&regmaster=&pt_uistyle=%s&r=%s";
            url = String.format(templateUrl, account, appid, ptlang, ptuistyle, Math.random());
            pageContent = HttpService.send(MoreHttpRequest.get(url), context);
            String[] responseData = new String[]{};
            Pattern resP = Pattern.compile("\\((.*)\\)");
            Matcher resM = resP.matcher(pageContent);
            if (resM.find()) {
                responseData = resM.group(1).substring(1, resM.group(1).length() - 1).replaceAll("'", "").split(",");
            }
            String ptvcodev1 = responseData[0];
            String verifyCode = responseData[1];
            String salt = responseData[2];
            String ptverifysessionv1 = responseData[3];
            String ptrandsalt = responseData[4];

            templateUrl = "https://ssl.ptlogin2.qq.com/login?pt_vcode_v1=%s&pt_verifysession_v1=%s&verifycode=%s&u=%s&p=%s&pt_randsalt=%s&ptlang=%s&low_login_enable=1&low_login_hour=720&u1=%s&from_ui=1&fp=loginerroralert&device=2&aid=%s&daid=4&pt_3rd_aid=0&ptredirect=1&h=1&g=1&pt_uistyle=%s&regmaster=&";
            // 把u1单独拿出来防止format()方法遇见"s%"会报错
            String u1 = "https://w.mail.qq.com/cgi-bin/login?vt=passport&vm=wsk&delegate_url=&f=xhtml&target=&ss=1";
            url = String.format(templateUrl, ptvcodev1, ptverifysessionv1, verifyCode, account, encryptPassword(password, verifyCode, ascii2Native(salt)), ptrandsalt, ptlang, URLEncoder.encode(u1, "UTF-8"), appid, ptuistyle);
            pageContent = HttpService.send(MoreHttpRequest.get(url), context);
            System.err.println(pageContent);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void webLoginTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", getResourceFilePath("chromedriver"));
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
//        options.addArguments("--proxy-server=http://125.121.136.177:6666");
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get(
                    "https://ui.ptlogin2.qq.com/cgi-bin/login?style=9&appid=522005705&daid=4&s_url=https%3A%2F%2Fw.mail.qq.com%2Fcgi-bin%2Flogin%3Fvt%3Dpassport%26vm%3Dwsk%26delegate_url%3D%26f%3Dxhtml%26target%3D&hln_css=http%3A%2F%2Fmail.qq.com%2Fzh_CN%2Fhtmledition%2Fimages%2Flogo%2Fqqmail%2Fqqmail_logo_default_200h.png&low_login=1");
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.jsReturnsValue("Object.defineProperties(navigator,{webdriver:{get:() => false}});return 0;"));

            String url = (String)wait.until(ExpectedConditions.jsReturnsValue("return document.getElementById('login_frame').src"));
            driver.get(url);
            driver.findElement(By.id("u")).sendKeys("jane29@qq.com");
            driver.findElement(By.id("p")).sendKeys("455409461#ABC");
            driver.findElement(By.id("go")).click();
            System.out.println(driver.getPageSource());
            System.out.println();
        } finally {
            Thread.sleep(10000);
            driver.quit();
        }
    }

    @Test
    public void testEncrypt() throws Exception {
        String password = "455409461#ABC";
        String verifyCode = "!VUQ";
        String salt = "\\xb1\\x6b\\x2f\\x4f\\x89\\x85\\x65\\x9d";
        System.out.println(encryptPassword(password, verifyCode, ascii2Native(salt)));
    }

    /*@Test
    public void testLogin() throws Exception {
        String referer = "https://ui.ptlogin2.qq.com/cgi-bin/login?style=9&appid=522005705&daid=4&s_url=https%3A%2F%2Fw.mail.qq" +
                ".com%2Fcgi-bin%2Flogin%3Fvt%3Dpassport%26vm%3Dwsk%26delegate_url%3D%26f%3Dxhtml%26target%3D&hln_css=http%3A%2F%2Fmail.qq.com%2Fzh_CN%2Fhtmledition%2Fimages%2Flogo%2Fqqmail%2Fqqmail_logo_default_200h.png&low_login=1";
        BasicCookieStore cookieStore = new BasicCookieStore();
        HttpClientContext context = HttpClientContext.create();
        context.setCookieStore(cookieStore);
        String proxy = "115.216.115.117:6666";
        //proxy = null;

        String username = "jane29@qq.com";
        String password = "455409461#ABC";
        String url = "https://ssl.ptlogin2.qq.com/check?pt_tea=2&uin=" + username + "&appid=522005705&ptlang=2052&regmaster=&pt_uistyle=9&r=" +
                Math.random();
        String pageContent = null;
        String pt_vcode_v1 = null;
        String verifycode = null;
        String salt = null;
        String pt_verifysession_v1 = null;
        String pt_randsalt = null;
        while (true) {
            pageContent = HttpService.send(MoreHttpRequest.get(url).setReferer(referer).setProxy(proxy), context);
            System.out.println(pageContent);
            pt_vcode_v1 = PatternUtils.group(pageContent, "ptui_checkVC\\('(\\d+)','([^']+)','([^']+)','([^']*)','([^']+)'\\)", 1);
            verifycode = PatternUtils.group(pageContent, "ptui_checkVC\\('(\\d+)','([^']+)','([^']+)','([^']*)','([^']+)'\\)", 2);
            salt = PatternUtils.group(pageContent, "ptui_checkVC\\('(\\d+)','([^']+)','([^']+)','([^']*)','([^']+)'\\)", 3);
            pt_verifysession_v1 = PatternUtils.group(pageContent, "ptui_checkVC\\('(\\d+)','([^']+)','([^']+)','([^']*)','([^']+)'\\)", 4);
            pt_randsalt = PatternUtils.group(pageContent, "ptui_checkVC\\('(\\d+)','([^']+)','([^']+)','([^']*)','([^']+)'\\)", 5);
            if (StringUtils.isBlank(pt_verifysession_v1)) {
                break;
            }
        }

        String checkUrl = "https://ssl.captcha.qq.com/cap_union_prehandle?aid=522005705&captype=&curenv=inner&protocol=https&clientype=1" +
                "&disturblevel=&apptype=2&noheader=0&color=&showtype=&fb=1&theme=&lang=2052&grayscale=1&cap_cd=" + verifycode + "&uid=" + username +
                "&wxLang=&subsid" + "=1&callback=_aq_273498&sess=";
        pageContent = HttpService.send(MoreHttpRequest.get(checkUrl).setReferer(referer).setProxy(proxy), context);
        System.out.println(pageContent);

        String result = PatternUtils.group(pageContent, "\\(([^\\)]+)\\)", 1);

        String sess = JsonPathUtil.readAsString(result, "$.sess");
        String sid = JsonPathUtil.readAsString(result, "$.sid");

        String seleniumUrl = "https://ssl.captcha.qq.com/cap_union_new_show?aid=522005705&captype=&curenv=inner&protocol=https&clientype=1" +
                "&disturblevel=&apptype=2&noheader=0&color=&showtype=&fb=1&theme=&lang=2052&grayscale=1&subsid=2&sess=" + sess + "&fwidth=0&sid" +
                "=" + sid + "&forcestyle=undefined&wxLang=&tcScale=1&uid=" + username + "&cap_cd=" + verifycode +
                "&rnd=581171&TCapIframeLoadTime=336" + "&prehandleLoadTime=235&createIframeStart=1554271009326";
        List<Cookie> cookies = context.getCookieStore().getCookies();

        System.setProperty("webdriver.gecko.driver", "/Users/guimeichao/GithubSaas/test-project/src/main/resources/geckodriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://ui.ptlogin2.qq.com/style/8/images/checked.png");
        for (org.apache.http.cookie.Cookie cookie : cookies) {
            driver.manage().addCookie(new org.openqa.selenium.Cookie(cookie.getName(), cookie.getValue(), cookie.getDomain(), cookie.getPath(),
                    cookie.getExpiryDate()));
        }

        driver.get(seleniumUrl);
        //huaDong(driver);
        new WebDriverWait(driver, 10).until(ExpectedConditions.jsReturnsValue("window.postMessage=function(e){window.ticket=e};return 0;"));
        WebElement slideBg = driver.findElement(By.id("slideBg"));
        WebElement slideBlock = driver.findElement(By.id("slideBlock"));
        String ticket = "";
        String cc = "";
        while (null != slideBg && null != slideBlock) {
            int bgW = slideBg.getSize().getWidth();
            int bgX = slideBg.getLocation().getX();
            int blockX = slideBlock.getLocation().getX();
            while (blockX < bgX) {
                Thread.sleep(1000);
                slideBlock = driver.findElement(By.id("slideBlock"));
                blockX = slideBlock.getLocation().getX();
            }
            String slideBgUrl = slideBg.getAttribute("src");
            String slideBlockUrl = slideBlock.getAttribute("src");
            logger.info("slideBgUrl={}", slideBgUrl);
            logger.info("slideBlockUrl={}", slideBlockUrl);
            byte[] img1 = downImage(slideBgUrl);
            byte[] img2 = downImage(slideBlockUrl);
            FileUtils.writeByteArrayToFile(new File("data/1.jpeg"), img1);
            FileUtils.writeByteArrayToFile(new File("data/2.png"), img2);
            BufferedImage image1 = ImageIO.read(new ByteArrayInputStream(img1));
            BufferedImage image2 = ImageIO.read(new ByteArrayInputStream(img2));
            int bgPicW = image1.getWidth();
            int x = getX(image1, getHeight(image2));
            if (x == 0) {
                driver.findElement(By.id("reload")).click();
                slideBg = driver.findElement(By.id("slideBg"));
                slideBlock = driver.findElement(By.id("slideBlock"));
                continue;
            }
            int offset = x * bgW / bgPicW + bgX - blockX;
            WebElement element = driver.findElement(By.id("tcaptcha_drag_thumb"));
            Actions actions = new Actions(driver);
            actions.clickAndHold(element).perform();
            actions.moveByOffset(offset, RandomUtils.nextInt(1, 5)).click().perform();
            String ticketStr = (String) new WebDriverWait(driver, 10).until(ExpectedConditions.jsReturnsValue("return window.ticket;"));
            ticket = JsonPathUtil.readAsString(ticketStr, "$.message.ticket");
            cc = JsonPathUtil.readAsString(ticketStr, "$.message.randstr");
            if (StringUtils.isNotBlank(ticket) && StringUtils.isNotBlank(cc)) {
                break;
            }

        }

        InputStream inputStream = OperatorTest.class.getClassLoader().getResourceAsStream("encryptjs/qqqq.encryptjs");
        Invocable invocable = createInvocable(inputStream, "GBK");
        String encryptPassword = invocable.invokeFunction("entry", password, ascii2Native(salt), cc).toString();

        url = "https://ssl.ptlogin2.qq.com/login?pt_vcode_v1=" + pt_vcode_v1 + "&pt_verifysession_v1" + "=" + ticket + "&verifycode=" + cc + "&u=" +
                username + "&p=" + encryptPassword + "&pt_randsalt=" + pt_randsalt +
                "&ptlang=2052&low_login_enable=1&low_login_hour=720&u1=https%3A%2F%2Fw.mail.qq.com%2Fcgi-bin%2Flogin%3Fvt%3Dpassport%26vm%3Dwsk%26delegate_url%3D%26f%3Dxhtml%26target%3D%26ss%3D1&from_ui=1&fp=loginerroralert&device=2&aid=522005705&daid=4&pt_3rd_aid=0&ptredirect=1&h=1&g=1&pt_uistyle=9&regmaster=&";
        System.out.println(url);
        pageContent = HttpService.send(MoreHttpRequest.get(url).setReferer(referer).setProxy(proxy), context);
        System.out.println(pageContent);

        String redirectUrl = PatternUtils.group(pageContent, "ptuiCB\\('[^']+','[^']+','([^']+)','[^']+','登录成功！', '[^']+'\\)", 1);
        pageContent = HttpService.send(MoreHttpRequest.get(redirectUrl).setReferer(referer).setProxy(proxy), context);
        System.out.println(pageContent);
        String mainUrl = "https://w.mail.qq.com/cgi-bin/login?vt=passport&vm=wsk&delegate_url=&f=xhtml&target=&ss=1";
        pageContent = HttpService.send(MoreHttpRequest.get(mainUrl).setReferer(referer).setProxy(proxy), context);
        System.out.println(pageContent);
        String urlStr = XPathUtil.getXpath("//meta[@http-equiv='Refresh']/@content", pageContent).get(0);
        String todayUrl = urlStr.replaceAll("^0;url=", "");
        pageContent = HttpService.send(MoreHttpRequest.get(todayUrl).setReferer(referer).setProxy(proxy), context);
        System.out.println(pageContent);

    }*/

    private String encryptPassword(String password, String verifyCode, String salt) throws Exception {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("encryptjs/emailqq.js");
        Invocable invocable = createInvocable(inputStream, "GBK");
        return invocable.invokeFunction("entry", password, salt, verifyCode).toString();
    }

    private static Invocable createInvocable(InputStream inputStream, String charsetName) throws Exception {
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");
        scriptEngine.eval(new InputStreamReader(inputStream, charsetName));
        return (Invocable) scriptEngine;
    }

    public static String ascii2Native(String str) {
        StringBuilder sb = new StringBuilder();
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            char c = ascii2Char(matcher.group(1));
            sb.append(c);
        }
        return sb.toString();
    }

    private static char ascii2Char(String str) {
        if (str.length() > 2) {
            String tmp = str.substring(0, str.length() - 2);
            int code = Integer.parseInt(tmp, 16) << 8;
            tmp = str.substring(str.length() - 2, str.length());
            code += Integer.parseInt(tmp, 16);
            return (char) code;
        } else {
            return (char) Integer.parseInt(str, 16);
        }
    }

    @Test
    public void testHuaKuai() throws InterruptedException {
        //System.setProperty("webdriver.gecko.driver", "/Users/guimeichao/GithubSaas/test-project/src/main/resources/geckodriver");
        //WebDriver driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", getResourceFilePath("chromedriver"));
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        WebDriver driver = new ChromeDriver(options);
        driver.get(
                "https://ssl.captcha.qq.com/cap_union_new_show?aid=522005705&captype=&curenv=inner&protocol=https&clientype=1&disturblevel=&apptype" +
                        "=2&noheader=0&color=&showtype=&fb=1&theme=&lang=2052&grayscale=1&subsid=2&sess" +
                        "=gUOznQvC6XgC0yBnLvMnjG9Et15Li2ItLNpZG9t7ZrjkzxpmevsnAdQf7dJRaQaZ3bDDTbAaUJnlNX04ZbCER0cSSjvWHnT" + "-*&fwidth=0&sid" +
                        "=6675572218139251506&forcestyle=undefined&wxLang=&tcScale=1&uid=jane2922e@qq.com&cap_cd=hsEfmNL6o4g9c_9WC3JIZpJMOyvd02ng4VXwh2s3ed0MZ9D8Bv6ZhA**&rnd=581171&TCapIframeLoadTime=336&prehandleLoadTime=235&createIframeStart=1554271009326");
        huaDong(driver);
        driver.quit();
    }

    private void huaDong(WebDriver driver) throws InterruptedException {
        WebElement element = driver.findElement(By.id("tcaptcha_drag_thumb"));
        System.out.println("tcaptcha_drag_thumb：【 " + element.getLocation().getX() + ", " + element.getLocation().getY() + "]");

        WebElement element1 = driver.findElement(By.id("slideBlock"));
        System.out.println("slideBlock：【 " + element1.getLocation().getX() + ", " + element1.getLocation().getY() + "]");

        WebElement element2 = driver.findElement(By.id("slideBg"));
        System.out.println("slideBg：【 " + element2.getLocation().getX() + ", " + element2.getLocation().getY() + "]");

        element = driver.findElement(By.id("tcaptcha_drag_thumb"));
        System.out.println("tcaptcha_drag_thumb：【 " + element.getLocation().getX() + ", " + element.getLocation().getY() + "]");

        element1 = driver.findElement(By.id("slideBlock"));
        System.out.println("slideBlock：【 " + element1.getLocation().getX() + ", " + element1.getLocation().getY() + "]");

        Actions actions = new Actions(driver);
        int move = 190;
        while (true) {
            actions.clickAndHold(element).perform();
            for (int i = 0; i < move; i++) {
                int sleep = RandomUtils.nextInt(50, 100);
                Thread.sleep(sleep);
                int offset = RandomUtils.nextInt(10, 50);
                i = i + offset;
                try {
                    if (i > move) {
                        actions.moveByOffset(0, RandomUtils.nextInt(1, 5)).click().perform();
                        break;
                    }
                    actions.moveByOffset(offset, RandomUtils.nextInt(1, 5)).perform();
                } catch (WebDriverException e) {
                    System.out.println("滑动成功");
                    break;
                }
            }
            move = move + 10;
            System.out.println();
        }

    }

    public static byte[] downImage(String url) {
        try {
            return IOUtils.toByteArray(new URI(url));
        } catch (Exception e) {
            logger.error("down image error url={}", url, e);
            return null;
        }
    }

    private int getHeight(BufferedImage image) throws IOException {
        int width1 = image.getWidth();
        int height1 = image.getHeight();
        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;
        boolean sX = false;
        for (int i = 0; i < width1; i++) {
            for (int j = 0; j < height1; j++) {
                int rgb = image.getRGB(i, j);
                int[] rgb1 = new int[3];
                rgb1[0] = (rgb & 0xff0000) >> 16;
                rgb1[1] = (rgb & 0xff00) >> 8;
                rgb1[2] = (rgb & 0xff);
                if (rgb1[0] > 200 && rgb1[1] > 200 && rgb1[2] > 200 && !sX) {
                    startX = i;
                    startY = j;
                    sX = true;
                } else if (rgb1[0] > 200 && rgb1[1] > 200 && rgb1[2] > 200) {
                    endX = i;
                    endY = j;
                }
            }
            if (startX != 0 || startY != 0) {
                System.out.println("Start:[" + startX + ", " + startY + "]");
                System.out.println("End:[" + endX + ", " + endY + "]");
                return endY - startY;
            }
            sX = false;
        }
        return 0;
    }

    private int getX(BufferedImage image, int height) throws IOException {
        int width1 = image.getWidth();
        int height1 = image.getHeight();
        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;
        boolean sX = false;
        for (int i = 0; i < width1; i++) {
            for (int j = 0; j < height1; j++) {
                int rgb = image.getRGB(i, j);
                int[] rgb1 = new int[3];
                rgb1[0] = (rgb & 0xff0000) >> 16;
                rgb1[1] = (rgb & 0xff00) >> 8;
                rgb1[2] = (rgb & 0xff);
                if (rgb1[0] > 180 && rgb1[1] > 180 && rgb1[2] > 180 && !sX) {
                    startX = i;
                    startY = j;
                    sX = true;
                } else if (rgb1[0] > 180 && rgb1[1] > 180 && rgb1[2] > 180) {
                    endX = i;
                    endY = j;
                }
            }
            if (startX != 0 || startY != 0) {
                if ((endY - startY) >= (height - 20) && (endY - startY) <= (height + 20)) {
                    if (startX > 450) {
                        System.out.println("Start:[" + startX + ", " + startY + "]");
                        System.out.println("End:[" + endX + ", " + endY + "]");
                        return startX - 23;
                    }
                }
                sX = false;
            }
        }
        return 0;
    }

    private String getResourceFilePath(String fileName) {
        return this.getClass().getResource("/" + fileName).getPath();
    }

    /*private String getProxy() {
        String proxyStr = proxy.getIp() + ":" + proxy.getPort();
        Proxy p = new org.openqa.selenium.Proxy();
        p.setSslProxy(proxyStr);
        p.setHttpProxy(proxyStr);
        capabilities.setCapability(CapabilityType.PROXY, p);
    }*/

}
