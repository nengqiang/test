package com.hnq.study.mq;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.hnq.study.consts.Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @author henengqiang
 * @date 2018/8/13
 */
@Service
@Slf4j
public class MqProducer {

    @Autowired
    private Config config;

    private DefaultMQProducer producer;

    @PostConstruct
    public void init() throws MQClientException {
        producer = new DefaultMQProducer(config.getGroupName());
        producer.setNamesrvAddr(config.getNamesrvAddr());
        producer.setMaxMessageSize(config.getMaxMessageSize());
        producer.start();
    }

    @PreDestroy
    public void destroy() {
        producer.shutdown();
    }

    public boolean send(String body, String topic, String tag, String key) {
        Message message = new Message(topic, tag, key, body.getBytes());
        return send(message);
    }

    public boolean send(Message message) {
        if (StringUtils.isBlank(message.getKeys())) {
            message.setKeys(UUID.randomUUID().toString());
        }
        try {
            SendResult sendResult = producer.send(message);
            log.info("===》已发送消息:[topic={}, tag={}, key={}, info={}]\n",
                    message.getTopic(), message.getTags(), message.getKeys(), sendResult.toString());
            return sendResult.getSendStatus() == SendStatus.SEND_OK;
        } catch (InterruptedException | RemotingException | MQClientException | MQBrokerException e) {
            log.error("==》发送消息失败:[topic={}, key={}, body={}]\n",
                    message.getTopic(), message.getKeys(), new String(message.getBody(), StandardCharsets.UTF_8), e);
            return false;
        }
    }

}
