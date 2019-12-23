package com.hnq.study;

import com.hnq.study.service.CacheService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author henengqiang
 * @date 2018/11/1
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LocalCacheApplication.class})
class CacheTests {

    @Autowired
    private CacheService cacheService;

    @Test
    void getCacheDataTest() {
        try {
            for (int i = 0; i < 20; i++) {
                System.out.println(cacheService.getStr("30"));
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
