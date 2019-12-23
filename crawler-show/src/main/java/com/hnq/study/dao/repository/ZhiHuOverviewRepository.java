package com.hnq.study.dao.repository;

import com.google.common.collect.Lists;
import com.hnq.study.dao.domain.ZhiHuOverview;
import com.hnq.study.dao.mapper.ZhiHuOverviewMapper;
import com.hnq.study.dao.mapper.ext.ZhiHuOverviewExtMapper;
import com.hnq.study.dao.model.ZhiHuOverviewDO;
import com.hnq.toolkit.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author henengqiang
 * @date 2019/05/08
 */
@Service
public class ZhiHuOverviewRepository {

    @Autowired
    private ZhiHuOverviewMapper zhiHuOverviewMapper;

    @Autowired
    private ZhiHuOverviewExtMapper zhiHuOverviewExtMapper;

    public int insert(String taskId, ZhiHuOverviewDO zhiHuOverviewDO) {
        ZhiHuOverview zhiHuOverview = new ZhiHuOverview();
        BeanUtils.copyProperties(zhiHuOverviewDO, zhiHuOverview);
        zhiHuOverview.setTaskId(Long.valueOf(taskId));
        return zhiHuOverviewMapper.insert(zhiHuOverview);
    }

    public void insertBatch(String taskId, List<ZhiHuOverviewDO> zhiHuOverviewBOS) {
        List<ZhiHuOverview> zhiHuOverviews = Lists.newArrayList();
        for (ZhiHuOverviewDO zhiHuOverviewDO : zhiHuOverviewBOS) {
            ZhiHuOverview zhiHuOverview = new ZhiHuOverview();
            BeanUtils.copyProperties(zhiHuOverviewDO, zhiHuOverview);
            zhiHuOverview.setTaskId(Long.valueOf(taskId));
            zhiHuOverviews.add(zhiHuOverview);
        }
        zhiHuOverviewExtMapper.insertBatch(zhiHuOverviews);
    }

}
