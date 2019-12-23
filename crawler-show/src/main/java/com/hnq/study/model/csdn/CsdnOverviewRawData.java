package com.hnq.study.model.csdn;

import lombok.Data;

/**
 * @author henengqiang
 * @date 2019/05/09
 */
@Data
public class CsdnOverviewRawData {

    /**
     * 平台
     */
    private String platForm;

    /**
     * 爬取关键字
     */
    private String keyword;

    /**
     * 爬取的问题
     */
    private String title;

    /**
     * 回答这个问题的作者的昵称
     */
    private String author;

    /**
     * 阅读数
     */
    private String readers;

    /**
     * 作者观点链接
     */
    private String link;

    /**
     * 发布时间
     */
    private String time;

    /**
     * 作者对问题的观点
     */
    private String overview;

}
