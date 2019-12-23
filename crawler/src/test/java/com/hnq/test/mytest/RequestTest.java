package com.hnq.test.mytest;

import com.hnq.test.common.CrawlUtils;
import com.hnq.toolkit.util.FileUtils;
import com.hnq.toolkit.util.ThreadPoolUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;

/**
 * @author henengqiang
 * @date 2019/06/19
 */
@Slf4j
public class RequestTest {

    @Test
    public void requestTest() {
//        String url = "http://wap.hn.10086.cn/wap/static/login/Login.html";
        String url = "http://www.hn.10086.cn/newservice/static/componant/login.html";
        System.setProperty("webdriver.chrome.driver", FileUtils.getResourceFilePath(RequestTest.class,"chromedriver"));
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        String proxy = CrawlUtils.getProxyStr();
//        String proxy = "183.164.238.126:6666";
        options.addArguments("--proxy-server=http://" + proxy);

        // 驱动chrome以手机模拟器方式打开wap页面
//        Map<String, String> mobileEmulation = Maps.newHashMap();
//        mobileEmulation.put("deviceName", "iPhone 6 Plus");
//        options.setExperimentalOption("mobileEmulation", mobileEmulation);
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get(url);
//            log.debug(driver.getPageSource());

            Thread.sleep(200000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            ThreadPoolUtils.execute(new Task());
        }
        ThreadPoolUtils.shutdown();
    }

    private static class Task implements Runnable {
        @Override
        public void run() {
            new RequestTest().requestTest();
        }
    }


}
