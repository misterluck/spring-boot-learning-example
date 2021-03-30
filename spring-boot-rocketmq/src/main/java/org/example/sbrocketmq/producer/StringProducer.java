package org.example.sbrocketmq.producer;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.example.sbrocketmq.topic.TopicConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 01 RocketMQ生产字符串
 */
@Component
public class StringProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void syncSend(Object payload) {
        SendResult sendResult = rocketMQTemplate.syncSend(TopicConstants.StringTopic, payload);
        System.out.printf("syncSend1 to topic %s sendResult=%s %n", TopicConstants.StringTopic, sendResult);

        sendResult = rocketMQTemplate.syncSend(TopicConstants.StringTopic, MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
        System.out.printf("syncSend2 to topic %s sendResult=%s %n", TopicConstants.StringTopic, sendResult);

    }

}
