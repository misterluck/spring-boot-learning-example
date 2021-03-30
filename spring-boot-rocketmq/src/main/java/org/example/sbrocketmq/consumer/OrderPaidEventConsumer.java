package org.example.sbrocketmq.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.example.sbrocketmq.domain.OrderPaidEvent;
import org.example.sbrocketmq.topic.TopicConstants;
import org.springframework.stereotype.Service;

/**
 * 03 RocketMQ消费OrderPaidEvent
 */
@Service
@RocketMQMessageListener(topic = TopicConstants.OrderPaidTopic, consumerGroup = "order_paid_consumer")
public class OrderPaidEventConsumer implements RocketMQListener<OrderPaidEvent> {

    @Override
    public void onMessage(OrderPaidEvent orderPaidEvent) {
        System.out.printf("------- OrderPaidEventConsumer received: [orderId : %s] [paidMoney : %s] \n", orderPaidEvent.getOrderId(), orderPaidEvent.getPaidMoney());
    }
}
