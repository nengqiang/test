package com.hnq.test.login;

import com.hnq.test.common.CrawlUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.interactions.Actions;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author henengqiang
 * @date 2019/04/22
 */
public class CommonMethods {

    public void replaceSrcAndPrompt(String templateHtml, String src, String prompt) throws IOException {
        String html = org.apache.commons.io.FileUtils.readFileToString(new File(getResourceFilePath(templateHtml)), "UTF-8");
        html = html.replaceAll("src=\".*.jpg\"", "src=\"" + src + "\"");
        html = html.replaceAll("<span id=\"prompt\">.*</span>", "<span id=\"prompt\">" + prompt + "</span>");
        FileUtils.writeStringToFile(new File("/Users/hanif/studyProjects/test/crawler/src/test/resources/picTarget.html"), html, "UTF-8");
    }

    public String getResourceFilePath(String fileName) {
        return this.getClass().getResource("/" + fileName).getPath();
    }


    /*
     * --------------------------------------------------------
     */

    private static List<Integer> arraysX = new ArrayList<>();

    private static List<Integer> arraysY = new ArrayList<>();

    public void move(Actions actions, int x1, int y1, int x2, int y2) throws InterruptedException {
        int flagX = x2 > x1 ? 1 : -1;
        int flagY = y2 > y1 ? 1 : -1;
        arraysX = new ArrayList<>();
        getRandomArrayX(Math.abs(x2 - x1));

        arraysY = new ArrayList<>();
        getRandomArrayY(Math.abs(y2 - y1), arraysX.size());

        for (int i = 0; i < arraysX.size(); i++) {
            int offsetX = arraysX.get(i);
            int offsetY = arraysY.get(i);
            Thread.sleep(RandomUtils.nextInt(10, 15));
            if (i == arraysX.size() - 1) {
                actions.moveByOffset(offsetX * flagX, offsetY * flagY).click().perform();
            } else {
                actions.moveByOffset(offsetX * flagX, offsetY * flagY).perform();
            }

        }

    }

    private void getRandomArrayX(int sum) {
        int i = RandomUtils.nextInt(1, 10);
        int j = sum - i;
        if (j <= 0) {
            arraysX.add(sum);
        } else {
            arraysX.add(i);
            getRandomArrayX(j);
        }
    }

    private void getRandomArrayY(int sum, int n) {
        int i = RandomUtils.nextInt(sum / n / 2, sum / n * 2);
        int j = sum - i;
        if (j <= 0) {
            arraysY.add(sum);
        } else {
            arraysY.add(i);
            if (n > 1) {
                getRandomArrayY(j, n - 1);
            }
        }

    }


    /**
     * use {@link CrawlUtils#createInvocable(java.lang.String, java.lang.String)} instead
     */
    @Deprecated
    public Invocable createInvocable(InputStream inputStream, String charsetName) throws Exception {
        if (StringUtils.isBlank(charsetName)) {
            throw new IllegalArgumentException("empty charsetName");
        }
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");
        scriptEngine.eval(new InputStreamReader(inputStream, charsetName));
        return (Invocable) scriptEngine;
    }

}
