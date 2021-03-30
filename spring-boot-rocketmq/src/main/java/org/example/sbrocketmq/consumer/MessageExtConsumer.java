package org.example.sbrocketmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.UtilAll;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.example.sbrocketmq.topic.TagConstants;
import org.example.sbrocketmq.topic.TopicConstants;
import org.springframework.stereotype.Service;

/**
 * 04 RocketMQ消费MessageExt
 */
@Service
@RocketMQMessageListener(topic = TopicConstants.MessageExtTopic, selectorExpression = TagConstants.MessageExtTopicTag1+"||"+TagConstants.MessageExtTopicTag2, consumerGroup = "message_ext_consumer")
public class MessageExtConsumer implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {

    @Override
    public void onMessage(MessageExt message) {
        System.out.printf("------- MessageExtConsumer received message, msgId: %s, body:%s \n", message.getMsgId(), new String(message.getBody()));
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        // set consumer consume message from now
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP);
        consumer.setConsumeTimestamp(UtilAll.timeMillisToHumanString3(System.currentTimeMillis()));
    }

}
