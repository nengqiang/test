package com.hnq.test.toutiao;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.hnq.test.toutiao.common.Constants;
import com.hnq.test.toutiao.search.IShopSearch;
import com.hnq.test.toutiao.search.ShopSearchImpl;
import com.hnq.test.toutiao.util.CrawlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author henengqiang
 * @date 2019/08/01
 */
@Slf4j
public class ShopIdSearchService {

    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            100,
            250,
            30,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(32),
            new ThreadFactoryBuilder().setNameFormat("hanif-pool-%d").build(),
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    /**
     * 脑洞历史观 137万粉丝
     */
    private static final String USER_ID = "4098955063";

    /**
     * 今日头条官方账号 1448万粉丝
     */
    private static final String TOUTIAO_USER_ID = "3612927328";

    private static final int COUNT = 49;

    private static final int SIZE = COUNT;

    private static final int REPETITIONS = 10;

    private IShopSearch shopSearch = new ShopSearchImpl();

    private static final String FILE_PATH = "/Users/hanif/studyProjects/test/crawler/src/main/resources/toutiao/shopIds.csv";

    private static final String USER_ID_FILE_PATH = "/Users/hanif/studyProjects/test/crawler/src/main/resources/toutiao/userIds.csv";

    private static File file = new File(FILE_PATH);

    private static File userIdFile = new File(USER_ID_FILE_PATH);

    public void searchUserIdAndShopId() {
        int page = 13906;
        int offset = page * COUNT;
        String proxy = CrawlUtils.getProxyStr();
        String randomUrl = Constants.randomFansUrl();
        do {
            List<String> userIds = new ArrayList<>(COUNT * 4 / 3 + 1);
            try {
                if (page % 50 == 0) {
                    proxy = CrawlUtils.getProxyStr();
                    randomUrl = Constants.randomFansUrl();
                }
                List<String> array = shopSearch.searchToutiaoUserFanIds(randomUrl, proxy, TOUTIAO_USER_ID, offset, COUNT);

                /*if (CollectionUtils.isEmpty(array)) {
                    break;
                }*/

                int repetition = 0;
                for (String userId : array) {
                    if (userIds.contains(userId)) {
                        repetition++;
                        continue;
                    }
                    userIds.add(userId);
                }

                if (repetition >= REPETITIONS) {
                    break;
                }

                threadPool.execute(() -> {
                    /*saveUserIds(userIds);*/
                    searchAndSaveShopId(userIds);
                });

                Thread.sleep(500);
            } catch (Exception e) {
                log.error("第【{}】页userId搜索请求异常", (page + 1), e);
            } finally {
                log.info("--------------------------------------> page={}, offset={}", (page + 1), offset);
            }

            page++;
            offset += COUNT;
        } while (page < 300000);
    }

    public void searchShopIdByUserIds() {

    }

    public void searchAndSaveShopId(List<String> userIds) {
        try {
            List<String> shopIds = shopSearch.searchUserShopId(userIds);
            if (CollectionUtils.isNotEmpty(shopIds)) {
                StringBuilder sb = new StringBuilder();
                for (String shopId : shopIds) {
                    sb.append(shopId).append(",").append("\n");
                }
                FileUtils.writeStringToFile(file, sb.toString(), StandardCharsets.UTF_8, true);
            }

        } catch (Exception e) {
            log.error("发掘shopId异常, userId={}", TOUTIAO_USER_ID, e);
        }
    }

    private void saveUserIds(List<String> userIds) {
        StringBuilder sb = new StringBuilder();
        for (String userId : userIds) {
            sb.append(userId).append(",").append("\n");
        }
        try {
            FileUtils.writeStringToFile(userIdFile, sb.toString(), StandardCharsets.UTF_8, true);
        } catch (IOException e) {
            log.error("userId保存失败, userIds={}", userIds, e);
        }
    }

}
