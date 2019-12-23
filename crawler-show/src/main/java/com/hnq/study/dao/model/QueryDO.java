package com.hnq.study.dao.model;

import lombok.Data;

import java.util.Date;

/**
 * @author henengqiang
 * @date 2019/05/10
 */
@Data
public class QueryDO {

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

    private Date startPublicTime;

    private Date endPublicTime;

    private Date startEditTime;

    private Date endEditTime;
}
