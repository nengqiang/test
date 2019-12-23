package com.hnq.study.model.zhihu;

import lombok.Data;

import java.util.Date;

/**
 * Business Object 业务对象主要作用是把业务逻辑封装为一个对象。这个对象可以包括一个或多个其它的对象。
 * Service 输出
 *
 * @author henengqiang
 * @date 2019/05/08
 */
@Data
public class ZhiHuOverviewBO {

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
    private String agree;

    /**
     * 作者观点的被评论数
     */
    private String comments;

    /**
     * 作者观点链接
     */
    private String link;

    /**
     * 发布时间
     */
    private String publicTime;

    /**
     * 编辑时间
     */
    private String editTime;

    /**
     * 作者对问题的观点
     */
    private String overview;

}
