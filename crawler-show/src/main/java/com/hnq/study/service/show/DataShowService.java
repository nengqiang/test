package com.hnq.study.service.show;

import com.google.common.collect.Lists;
import com.hnq.study.dao.model.QueryDO;
import com.hnq.study.dao.repository.CsdnOverviewRepository;
import com.hnq.study.model.*;
import com.hnq.study.dao.model.CsdnOverviewDO;
import com.hnq.study.model.csdn.CsdnOverviewDTO;
import com.hnq.toolkit.util.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据输出控制 增删改查
 *
 * @author henengqiang
 * @date 2019/05/08
 */
@Service
@Slf4j
public class DataShowService {

    @Autowired
    private CsdnOverviewRepository csdnOverviewRepository;

    public OverviewTotalBO<CsdnOverviewDTO> queryCsdnOverview(QueryDTO queryDTO) {
        OverviewTotalBO<CsdnOverviewDTO> result = new OverviewTotalBO<>();
        QueryDO queryDO = new QueryDO();
        BeanUtils.copyProperties(queryDTO, queryDO);
        OverviewTotalBO<CsdnOverviewDO> overviewTotal =
            csdnOverviewRepository.selectCsdnOverviewByQuery(queryDO, queryDTO.getPageNum(), queryDTO.getPageSize());
        List<CsdnOverviewDTO> csdnOverviewDTOS = Lists.newArrayList();
        for (CsdnOverviewDO overview : overviewTotal.getOverviews()) {
            CsdnOverviewDTO csdnDTO = new CsdnOverviewDTO();
            BeanUtils.copyProperties(overview, csdnDTO);
            csdnOverviewDTOS.add(csdnDTO);
        }
        result.setTotal(overviewTotal.getTotal());
        result.setOverviews(csdnOverviewDTOS);
        return result;
    }

}
