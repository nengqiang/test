package com.hnq.study.service.clean;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.hnq.study.dao.repository.CsdnOverviewRepository;
import com.hnq.study.model.csdn.CsdnOverviewRawData;
import com.hnq.study.dao.model.CsdnOverviewDO;
import com.hnq.study.redis.RedisUtils;
import com.hnq.study.util.StrUtils;
import com.hnq.toolkit.consts.DateConsts;
import com.hnq.toolkit.util.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
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
public class CsdnDataCleaner implements DataCleaner<CsdnOverviewDO> {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private CsdnOverviewRepository csdnOverviewRepository;

    @Override
    public void storage(String key) {
        String taskId = key.split("\\.")[2];
        try {
            List<CsdnOverviewDO> csdnOverviewDOS = dataClean(key);
            log.info(">>> 开始保存csdn数据到数据库... taskId={}", taskId);
            StopWatch watch = new StopWatch();
            watch.start();
            csdnOverviewRepository.insertBatch(taskId, csdnOverviewDOS);
            watch.stop();
            log.info(">>> 保存csdn数据成功，taskId={}, 洗数耗时=【{}ms】", taskId, watch.getTime());
        } catch (Exception e) {
            log.error(">>> 保存csdn数据失败，taskId={}", taskId, e);
        }
    }

    @Override
    public List<CsdnOverviewDO> dataClean(String key) {
        String taskId = key.split("\\.")[2];
        log.info(">>> 开始从redis获取csdn数据... key={}", key);
        List<String> valueList = redisUtils.lRangeByJedis(key, 0, -1);
        List<CsdnOverviewRawData> rawDataList = Lists.newArrayList();
        List<CsdnOverviewDO> csdnOverviewDOS = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(valueList)) {
            for (String str : valueList) {
                CsdnOverviewRawData rawData = JSONObject.parseObject(str, CsdnOverviewRawData.class);
                rawDataList.add(rawData);
            }
            transferCsdnBOToDO(rawDataList, csdnOverviewDOS);
            log.info(">>> 从redis获取数据成功，key={}, taskId={}, 数据条数=【{}】", key, taskId, valueList.size());
        } else {
            log.error(">>> 从redis获取数据失败，key={}, taskId={}", key, taskId);
        }
        return csdnOverviewDOS;
    }

    private void transferCsdnBOToDO(List<CsdnOverviewRawData> rawDataList, List<CsdnOverviewDO> csdnOverviewDOS) {
        for (CsdnOverviewRawData rawData : rawDataList) {
            CsdnOverviewDO csdnDO = new CsdnOverviewDO();
            BeanUtils.copyProperties(rawData, csdnDO);
            csdnDO.setReaders(Integer.valueOf(rawData.getReaders()));
            // 过滤emoji
            csdnDO.setOverview(StrUtils.filterEmoji(csdnDO.getOverview()));
            try {
                csdnDO.setPublicTime(DateUtils.parseDate(rawData.getTime(), DateConsts.PATTERN_TIME_2));
            } catch (ParseException e) {
                log.error(">>> csdn数据发布时间转换出现错误", e);
            }
            csdnOverviewDOS.add(csdnDO);
        }
    }

}
