package com.hnq.study.model;

import lombok.Data;

import java.util.List;

/**
 * @author henengqiang
 * @date 2019/05/10
 */
@Data
public class OverviewTotalBO<T> {

    Integer total;

    List<T> overviews;

}
