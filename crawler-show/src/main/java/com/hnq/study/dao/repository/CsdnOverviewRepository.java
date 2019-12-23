package com.hnq.study.dao.repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hnq.study.dao.domain.CsdnOverview;
import com.hnq.study.dao.domain.CsdnOverviewCriteria;
import com.hnq.study.dao.mapper.CsdnOverviewMapper;
import com.hnq.study.dao.mapper.ext.CsdnOverviewExtMapper;
import com.hnq.study.dao.model.CsdnOverviewDO;
import com.hnq.study.model.OverviewTotalBO;
import com.hnq.study.dao.model.QueryDO;
import com.hnq.toolkit.util.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author henengqiang
 * @date 2019/05/09
 */
@Service
public class CsdnOverviewRepository {

    @Autowired
    private CsdnOverviewMapper csdnOverviewMapper;

    @Autowired
    private CsdnOverviewExtMapper csdnOverviewExtMapper;

    public int insert(String taskId, CsdnOverviewDO csdnOverviewDO) {
        CsdnOverview csdnOverview = new CsdnOverview();
        BeanUtils.copyProperties(csdnOverviewDO, csdnOverview);
        csdnOverview.setTaskId(Long.valueOf(taskId));
        return csdnOverviewMapper.insert(csdnOverview);
    }

    public void insertBatch(String taskId, List<CsdnOverviewDO> csdnOverviewDOS) {
        List<CsdnOverview> csdnOverviews = Lists.newArrayList();
        for (CsdnOverviewDO csdnOverviewDO : csdnOverviewDOS) {
            CsdnOverview csdnOverview = new CsdnOverview();
            BeanUtils.copyProperties(csdnOverviewDO, csdnOverview);
            csdnOverview.setTaskId(Long.valueOf(taskId));
            csdnOverviews.add(csdnOverview);
        }
        csdnOverviewExtMapper.insertBatch(csdnOverviews);
    }

    /**
     * 根据关键词进行模糊查询
     */
    public OverviewTotalBO<CsdnOverviewDO> selectCsdnOverviewByKeyword(String keyword, Integer pageNum, Integer pageSize) {
        CsdnOverviewCriteria example = new CsdnOverviewCriteria();
        CsdnOverviewCriteria.Criteria criteria = example.createCriteria();
        criteria.andKeywordLike("%" + keyword + "%");
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<CsdnOverview> csdnOverviewPageInfo = new PageInfo<>(csdnOverviewMapper.selectByExampleWithBLOBs(example));

        OverviewTotalBO<CsdnOverviewDO> overviewTotal = new OverviewTotalBO<>();
        overviewTotal.setTotal(new Long(csdnOverviewPageInfo.getTotal()).intValue());
        List<CsdnOverviewDO> csdnOverviewDOS = Lists.newArrayList();
        for (CsdnOverview csdnOverview : csdnOverviewPageInfo.getList()) {
            CsdnOverviewDO csdnDO = new CsdnOverviewDO();
            BeanUtils.copyProperties(csdnOverview, csdnDO);
            csdnOverviewDOS.add(csdnDO);
        }
        overviewTotal.setOverviews(csdnOverviewDOS);
        return overviewTotal;
    }

    public OverviewTotalBO<CsdnOverviewDO> selectCsdnOverviewByQuery(QueryDO queryDO, Integer pageNum, Integer pageSize) {
        CsdnOverviewCriteria example = new CsdnOverviewCriteria();
        CsdnOverviewCriteria.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(queryDO.getKeyword())) {
            criteria.andKeywordLike("%" + queryDO.getKeyword() + "%");
        }
        if (StringUtils.isNotBlank(queryDO.getTitle())) {
            criteria.andTitleLike("%" + queryDO.getTitle() + "%");
        }
        if (StringUtils.isNotBlank(queryDO.getAuthor())) {
            criteria.andAuthorLike("%" + queryDO.getAuthor() + "%");
        }
        if (queryDO.getStartPublicTime() != null) {
            criteria.andPublicTimeGreaterThanOrEqualTo(queryDO.getStartPublicTime());
        }
        if (queryDO.getEndPublicTime() != null) {
            criteria.andPublicTimeLessThan(queryDO.getEndPublicTime());
        }

        PageHelper.startPage(pageNum, pageSize);
        PageInfo<CsdnOverview> csdnOverviewPageInfo = new PageInfo<>(csdnOverviewMapper.selectByExampleWithBLOBs(example));

        OverviewTotalBO<CsdnOverviewDO> overviewTotal = new OverviewTotalBO<>();
        overviewTotal.setTotal(new Long(csdnOverviewPageInfo.getTotal()).intValue());
        List<CsdnOverviewDO> csdnOverviewDOS = Lists.newArrayList();
        for (CsdnOverview csdnOverview : csdnOverviewPageInfo.getList()) {
            CsdnOverviewDO csdnDO = new CsdnOverviewDO();
            BeanUtils.copyProperties(csdnOverview, csdnDO);
            csdnOverviewDOS.add(csdnDO);
        }
        overviewTotal.setOverviews(csdnOverviewDOS);
        return overviewTotal;
    }

}
