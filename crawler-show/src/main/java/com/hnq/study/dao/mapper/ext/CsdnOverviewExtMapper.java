package com.hnq.study.dao.mapper.ext;

import com.hnq.study.dao.domain.CsdnOverview;

import java.util.List;

public interface CsdnOverviewExtMapper {

    int insertBatch(List<CsdnOverview> records);

}