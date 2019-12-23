package com.hnq.study.mq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.hnq.study.consts.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author henengqiang
 * @date 2018/8/13
 */
@Service
@Slf4j
public class MqConsumer {

    @Autowired
    private Config config;

    @Autowired
    private RawDataMessageListener messageListener;

    /**
     * 一般在应用中都会采用push的方法来自动的消费信息
     */
    private DefaultMQPushConsumer dataConsumer;

    @PostConstruct
    public void start() throws MQClientException {
        dataConsumer = new DefaultMQPushConsumer(config.getGroupName());
        dataConsumer.setNamesrvAddr(config.getNamesrvAddr());
        dataConsumer.subscribe(config.getConsumerTopic(), config.getConsumerTag());
        dataConsumer.setConsumeMessageBatchMaxSize(1);
        dataConsumer.registerMessageListener(messageListener);
        dataConsumer.start();
        log.info("==》消费者启动:[nameServer={}, topic={}, tag={}]\n",
                config.getNamesrvAddr(), config.getConsumerTopic(), config.getConsumerTag());
    }

    @PreDestroy
    public void shutDown() {
        dataConsumer.shutdown();
        log.info("|==消费者关闭==|");
    }

}
