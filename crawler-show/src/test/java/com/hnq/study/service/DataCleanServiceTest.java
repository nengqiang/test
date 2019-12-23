package com.hnq.study.service;

import com.hnq.study.BaseTest;
import com.hnq.study.service.clean.CsdnDataCleaner;
import com.hnq.study.service.clean.ZhiHuDataCleaner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author henengqiang
 * @date 2019/05/08
 */
class DataCleanServiceTest extends BaseTest {

    @Autowired
    private ZhiHuDataCleaner zhiHuDataCleaner;

    @Autowired
    private CsdnDataCleaner csdnDataCleaner;

    @Test
    void zhiHuStorageTest() {
        String key = "opinionDetails.10327.849073620141594";
        zhiHuDataCleaner.storage(key);
    }

    @Test
    void csdnStorageTest() {
        String key = "opinionDetails.10318.606262703633147";
        csdnDataCleaner.storage(key);
    }

}