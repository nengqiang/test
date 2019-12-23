package com.hnq.study;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author henengqiang
 * @date 2019/05/06
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {CrawlerShowApp.class})
@Slf4j
public abstract class BaseTest {
}
