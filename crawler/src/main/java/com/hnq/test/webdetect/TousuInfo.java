package com.hnq.test.webdetect;

import java.util.Date;

/**
 * @author henengqiang
 * @date 2019/11/04
 */
public class TousuInfo {

    private String platform;

    private String keyword;

    private String link;

    private String title;

    private Date time;

    @Override
    public String toString() {
        return "TousuInfo{" +
                "platform='" + platform + '\'' +
                ", keyword='" + keyword + '\'' +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                ", time=" + time +
                '}';
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
