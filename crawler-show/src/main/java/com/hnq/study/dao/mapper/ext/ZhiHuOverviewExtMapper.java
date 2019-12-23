package com.hnq.study.dao.mapper.ext;

import com.hnq.study.dao.domain.ZhiHuOverview;

import java.util.List;

/**
 * @author henengqiang
 * @date 2019/05/07
 */
public interface ZhiHuOverviewExtMapper {

    int insertBatch(List<ZhiHuOverview> records);

}