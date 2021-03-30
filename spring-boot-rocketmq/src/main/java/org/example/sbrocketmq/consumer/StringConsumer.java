package org.example.sbrocketmq.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.example.sbrocketmq.topic.TopicConstants;
import org.springframework.stereotype.Service;

/**
 * 01 RocketMQ消费字符串
 */
@Service
@RocketMQMessageListener(topic = TopicConstants.StringTopic, consumerGroup = "string_consumer")
public class StringConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.out.printf("-------- StringConsumer received: %s \n", message);
    }

}
