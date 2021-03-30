package org.example.sbrocketmq.producer;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.example.sbrocketmq.domain.User;
import org.example.sbrocketmq.topic.TopicConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

/**
 * 02 RocketMQ生产User
 */
@Component
public class UserProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void syncSend() {
        SendResult sendResult = rocketMQTemplate.syncSend(TopicConstants.UserTopic, new User("Kitty", "女", (byte) 18));
        System.out.printf("syncSend1 to topic %s sendResult=%s %n", TopicConstants.UserTopic, sendResult);

        sendResult = rocketMQTemplate.syncSend(TopicConstants.UserTopic, MessageBuilder.withPayload(new User("Lester", "男", (byte) 21))
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE).build());
        System.out.printf("syncSend1 to topic %s sendResult=%s %n", TopicConstants.UserTopic, sendResult);

    }

}
