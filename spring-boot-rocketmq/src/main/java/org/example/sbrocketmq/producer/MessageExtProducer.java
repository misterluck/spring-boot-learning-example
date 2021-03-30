package org.example.sbrocketmq.producer;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.example.sbrocketmq.topic.TagConstants;
import org.example.sbrocketmq.topic.TopicConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 04 RocketMQ生产MessageExt
 */
@Component
public class MessageExtProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void convertAndSend () {

        rocketMQTemplate.convertAndSend(TopicConstants.MessageExtTopic + ":" + TagConstants.MessageExtTopicTag1, "I'm from tag0");  // tag0 will not be consumer-selected
        System.out.printf("syncSend topic %s tag %s %n", TopicConstants.MessageExtTopic, TagConstants.MessageExtTopicTag1);
        rocketMQTemplate.convertAndSend(TopicConstants.MessageExtTopic + ":" + TagConstants.MessageExtTopicTag2, "I'm from tag1");
        System.out.printf("syncSend topic %s tag %s %n", TopicConstants.MessageExtTopic, TagConstants.MessageExtTopicTag2);

    }

}
