package org.example.sbrocketmq.producer;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.example.sbrocketmq.domain.OrderPaidEvent;
import org.example.sbrocketmq.topic.TopicConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 03 RocketMQ生产OrderPaidEvent
 */
@Component
public class OrderPaidEventProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void asyncSend() {

        rocketMQTemplate.asyncSend(TopicConstants.OrderPaidTopic, new OrderPaidEvent("T_001", new BigDecimal("88.00")), new SendCallback() {
            @Override
            public void onSuccess(SendResult var1) {
                System.out.printf("async onSucess SendResult=%s %n", var1);
            }

            @Override
            public void onException(Throwable var1) {
                System.out.printf("async onException Throwable=%s %n", var1);
            }

        });

    }

}
