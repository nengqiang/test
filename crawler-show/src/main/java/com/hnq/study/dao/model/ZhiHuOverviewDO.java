package com.hnq.study.dao.model;

import lombok.Data;

import java.util.Date;

/**
 * Business Object
 *
 * @author henengqiang
 * @date 2019/05/08
 */
@Data
public class ZhiHuOverviewDO {

    /**
     * 爬取关键字
     */
    private String keyword;

    /**
     * 爬取的问题
     */
    private String question;

    /**
     * 回答这个问题的作者的昵称
     */
    private String author;

    /**
     * 作者观点的被赞同数
     */
    private Integer agree;

    /**
     * 作者观点的被评论数
     */
    private Integer comments;

    /**
     * 作者观点链接
     */
    private String link;

    /**
     * 发布时间
     */
    private Date publicTime;

    /**
     * 编辑时间
     */
    private Date editTime;

    /**
     * 作者对问题的观点
     */
    private String overview;

}
