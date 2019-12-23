package com.hnq.study.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.hnq.study.model.RedisKeyInfo;
import com.hnq.study.service.clean.CsdnDataCleaner;
import com.hnq.study.service.clean.ZhiHuDataCleaner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
import static com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus.RECONSUME_LATER;

/**
 * @author henengqiang
 * @date 2019/06/13
 */
@Service
@Slf4j
public class RawDataMessageListener implements MessageListenerConcurrently {

    @Autowired
    private CsdnDataCleaner csdnDataCleaner;

    @Autowired
    private ZhiHuDataCleaner zhiHuDataCleaner;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
        try {
            MessageExt msg = list.get(0);
            String body = new String(msg.getBody());
            log.info("接收消息为-{}", body);

            RedisKeyInfo redisKeyInfo = JSON.parseObject(body, RedisKeyInfo.class);
            if (StringUtils.isNotEmpty(redisKeyInfo.getCsdnOpinionDetailsKey())) {
                csdnDataCleaner.storage(redisKeyInfo.getCsdnOpinionDetailsKey());
            } else if (StringUtils.isNotEmpty(redisKeyInfo.getZhihuOpinionDetailsKey())) {
                zhiHuDataCleaner.storage(redisKeyInfo.getZhihuOpinionDetailsKey());
            } else {
                log.error("没有接收到正确的redisKey！");
            }

            return CONSUME_SUCCESS;
        } catch (Exception e) {
            // 重试一次，如果仍然失败则抛弃该消息并处理失败信息
            if (list.get(0).getReconsumeTimes() <= 1) {
                return RECONSUME_LATER;
            } else {
                e.printStackTrace();
            }
        }
        return CONSUME_SUCCESS;
    }

}
