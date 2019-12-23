package com.hnq.study.dao;

import com.hnq.study.BaseTest;
import com.hnq.study.dao.repository.CsdnOverviewRepository;
import com.hnq.study.dao.model.CsdnOverviewDO;
import com.hnq.study.model.OverviewTotalBO;
import com.hnq.study.dao.model.QueryDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author henengqiang
 * @date 2019/05/08
 */
class DaoTest extends BaseTest {

    @Autowired
    private CsdnOverviewRepository csdnOverviewRepository;

    @Test
    void zhiHuInsertBatchTest() {

    }

    @Test
    void csdnKeywordFindTest() {
        OverviewTotalBO<CsdnOverviewDO> overviews =
                csdnOverviewRepository.selectCsdnOverviewByKeyword("三打", 2, 10);
        System.out.println("总条数=" + overviews.getTotal());
        overviews.getOverviews().forEach(System.out::println);
    }

    @Test
    void csdnQueryTest() {
        QueryDO queryDO = new QueryDO();
        queryDO.setKeyword("三打");
        queryDO.setTitle("三");
        OverviewTotalBO<CsdnOverviewDO> overviews =
                csdnOverviewRepository.selectCsdnOverviewByQuery(queryDO, 2, 3);
        System.out.println("总条数=" + overviews.getTotal());
        overviews.getOverviews().forEach(System.out::println);
    }

}
