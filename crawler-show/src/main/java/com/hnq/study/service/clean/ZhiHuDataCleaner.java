package com.hnq.study.service.clean;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.hnq.study.dao.repository.ZhiHuOverviewRepository;
import com.hnq.study.model.zhihu.ZhiHuOverviewBO;
import com.hnq.study.dao.model.ZhiHuOverviewDO;
import com.hnq.study.redis.RedisUtils;
import com.hnq.study.util.StrUtils;
import com.hnq.toolkit.consts.DateConsts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * @author henengqiang
 * @date 2019/05/10
 */
@Service
@Slf4j
public class ZhiHuDataCleaner implements DataCleaner<ZhiHuOverviewDO> {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private ZhiHuOverviewRepository zhiHuOverviewRepository;

    @Override
    public void storage(String key) {
        String taskId = key.split("\\.")[2];
        try {
            List<ZhiHuOverviewDO> zhiHuOverviewDOS = dataClean(key);
            log.info(">>> 开始保存zhihu数据到数据库... taskId={}", taskId);
            StopWatch watch = new StopWatch();
            watch.start();
            zhiHuOverviewRepository.insertBatch(taskId, zhiHuOverviewDOS);
            watch.stop();
            log.info(">>> 保存zhihu数据成功，taskId={}, 洗数耗时=【{}ms】", taskId, watch.getTime());
        } catch (Exception e) {
            log.error(">>> 保存zhihu数据失败，taskId={}", taskId, e);
        }
    }

    @Override
    public List<ZhiHuOverviewDO> dataClean(String key) {
        String taskId = key.split("\\.")[2];
        log.info(">>> 开始从redis获取zhihu数据... key={}", key);
        List<String> valueList = redisUtils.lRangeByJedis(key, 0, -1);
        List<ZhiHuOverviewBO> zhiHuOverviewBOS = Lists.newArrayList();
        List<ZhiHuOverviewDO> zhiHuOverviewDOS = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(valueList)) {
            for (String str : valueList) {
                ZhiHuOverviewBO zhiHuOverviewBO = JSONObject.parseObject(str, ZhiHuOverviewBO.class);
                zhiHuOverviewBOS.add(zhiHuOverviewBO);
            }
            transferZhiHuBOToDO(zhiHuOverviewBOS, zhiHuOverviewDOS);
            log.info(">>> 从redis获取数据成功，key={}, taskId={}, 数据条数=【{}】", key, taskId, valueList.size());
        } else {
            log.error(">>> 从redis获取数据失败，key={}, taskId={}", key, taskId);
        }
        return zhiHuOverviewDOS;
    }

    private void transferZhiHuBOToDO(List<ZhiHuOverviewBO> zhiHuOverviewBOS, List<ZhiHuOverviewDO> zhiHuOverviewDOS) {
        for (ZhiHuOverviewBO zhiHuOverviewBO : zhiHuOverviewBOS) {
            ZhiHuOverviewDO zhiHuOverviewDO = new ZhiHuOverviewDO();
            zhiHuOverviewDO.setKeyword(zhiHuOverviewBO.getKeyword());
            zhiHuOverviewDO.setQuestion(zhiHuOverviewBO.getQuestion());
            zhiHuOverviewDO.setAuthor(zhiHuOverviewBO.getAuthor());
            String agreeStr = zhiHuOverviewBO.getAgree();
            if (agreeStr != null) {
                if ( StringUtils.contains(agreeStr, "K")) {
                    zhiHuOverviewDO.setAgree(calculateAgree(agreeStr));
                } else {
                    zhiHuOverviewDO.setAgree(Integer.valueOf(agreeStr));
                }
            }
            String comments = zhiHuOverviewBO.getComments();
            zhiHuOverviewDO.setComments(comments != null ? Integer.valueOf(comments) : 0);
            zhiHuOverviewDO.setLink(zhiHuOverviewBO.getLink());
            try {
                zhiHuOverviewDO.setPublicTime(DateUtils.parseDate(zhiHuOverviewBO.getEditTime(), DateConsts.DEFAULT_PATTERN));
            } catch (ParseException e) {
                log.error(">>> zhihu数据发布时间转换出现错误", e);
            }
            try {
                zhiHuOverviewDO.setEditTime(DateUtils.parseDate(zhiHuOverviewBO.getEditTime(), DateConsts.DEFAULT_PATTERN));
            } catch (ParseException e) {
                log.error(">>> zhihu数据编辑时间转换出现错误", e);
            }
            zhiHuOverviewDO.setOverview(StrUtils.filterEmoji(zhiHuOverviewBO.getOverview()));
            zhiHuOverviewDOS.add(zhiHuOverviewDO);
        }
    }

    private Integer calculateAgree(String agreeStr) {
        double temp = Double.valueOf(agreeStr.substring(0, agreeStr.length() - 1));
        return (int)(temp * 1000);
    }


}
