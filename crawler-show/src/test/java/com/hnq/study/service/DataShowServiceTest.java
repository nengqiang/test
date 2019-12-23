package com.hnq.study.service;

import com.hnq.study.BaseTest;
import com.hnq.study.model.csdn.CsdnOverviewDTO;
import com.hnq.study.model.OverviewTotalBO;
import com.hnq.study.model.QueryDTO;
import com.hnq.study.service.show.DataShowService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author henengqiang
 * @date 2019/05/10
 */
class DataShowServiceTest extends BaseTest {

    @Autowired
    private DataShowService dataShowService;

    @Test
    void csdnDataShowTest() {
        QueryDTO queryDTO = new QueryDTO();
        queryDTO.setKeyword("三打");
        queryDTO.setTitle("三");
        queryDTO.setPageNum(2);
        queryDTO.setPageSize(5);
        OverviewTotalBO<CsdnOverviewDTO> result = dataShowService.queryCsdnOverview(queryDTO);
        System.out.println("total=" + result.getTotal());
        result.getOverviews().forEach(System.out::println);
    }

}