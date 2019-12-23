package com.hnq.test.login;

import com.hnq.toolkit.util.HttpUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author henengqiang
 * @date 2019/11/25
 */
class ComplaintLogin {

    private static final String JUTOUSU_LOGIN_URL = "https://api.weibo.com/oauth2/authorize?client_id=3673594135&redirect_uri=https%3A%2F%2Fpassport.21cn.com%2Fapi%2Fbind%2Fbind.do%3FserviceId%3D0%26saveLogin%3D5184000%26accountType%3Dsina%26nickNameGenType%3DautoGen%26httpsOn%3D1%26returnUrl%3Dhttps%253A%252F%252Fts.21cn.com%252Ffront%252Fwap%252FthirdLoginCallBack.do%253Furl%253Dhttps%253A%252F%252Fts.21cn.com%252F%26referUrl%3Dhttps%253A%252F%252Fts.21cn.com%252F%26display%3Djson2&response_type=code";

    private static String account = "qudao@treefinance.com.cn";

    private static String pwd = "dashugfd2017";

    @Test
    void jutousuLoginFromWeiboTest() {
        System.setProperty("webdriver.chrome.driver", this.getClass().getResource("/" + "chromedriver").getPath());
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.addArguments("--proxy-server=" + HttpUtils.getProxyStr());
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get(JUTOUSU_LOGIN_URL);
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oauth_login_form")));

            driver.findElement(By.id("userId")).sendKeys(account);

            WebElement qrCover = driver.findElement(By.className("qr-cover"));
            if (!StringUtils.contains(qrCover.getAttribute("style"), "none")) {
                driver.findElement(By.className("qr-change-logo")).click();
            }

            driver.findElement(By.id("passwd")).sendKeys(pwd);
            driver.findElement(By.className("WB_btn_login")).click();
            TimeUnit.MILLISECONDS.sleep(500);
            qrCover = driver.findElement(By.className("qr-cover"));
            if (!StringUtils.contains(qrCover.getAttribute("style"), "none")) {
                driver.findElement(By.className("qr-change-logo")).click();
                driver.findElement(By.className("WB_btn_login")).click();
            }

            String cookie = getCookieString(driver);
            System.out.println(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                TimeUnit.SECONDS.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();
        }
    }

    @Test
    void blackcatLoginFromWeiboTest() {
        System.setProperty("webdriver.chrome.driver", this.getClass().getResource("/" + "chromedriver").getPath());
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.addArguments("--proxy-server=" + HttpUtils.getProxyStr());
        WebDriver driver = new ChromeDriver(options);
        try {
            String url = "https://tousu.sina.com.cn/";
            driver.get(url);
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ac-login")));

            WebElement logElement = driver.findElement(By.className("ac-login"));
            Actions actions = new Actions(driver);
            actions.moveToElement(logElement).perform();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='loginname']")));

            driver.findElement(By.xpath("//input[@name='loginname']")).sendKeys(account);
            driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pwd);
            driver.findElement(By.className("login_btn"));

            String cookie = getCookieString(driver);
            System.out.println(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                TimeUnit.SECONDS.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();
        }
    }

    private static String getCookieString(WebDriver driver) {
        Set<Cookie> cookies = driver.manage().getCookies();
        if (null != cookies && !cookies.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Cookie cookie : cookies) {
                sb.append("; ").append(cookie.getName()).append("=").append(cookie.getValue());
            }
            return sb.substring(2);
        }
        return null;
    }

    @Test
    void test() {
        System.setProperty("webdriver.chrome.driver", this.getClass().getResource("/" + "chromedriver").getPath());
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.addArguments("--proxy-server=" + HttpUtils.getProxyStr());
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://sogo.com");
            driver.findElement(By.id("settings-mask"));

            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();
        }
    }

}
