package org.example.sbrocketmq.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.example.sbrocketmq.domain.User;
import org.example.sbrocketmq.topic.TopicConstants;
import org.springframework.stereotype.Service;

/**
 * 02 RocketMQ消费User
 */
@Service
@RocketMQMessageListener(topic = TopicConstants.UserTopic, consumerGroup = "user_consumer")
public class UserConsumer implements RocketMQListener<User> {

    @Override
    public void onMessage(User user) {
        System.out.printf("######## user_consumer received: %s ; age: %s ; name: %s ; sex: %s \n", user, user.getAge(), user.getName(), user.getSex());
    }
}
