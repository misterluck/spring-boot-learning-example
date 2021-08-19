package org.example.sbredis.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

@Configuration
public class RedisMessageListener {

    /**
     * 创建连接工厂
     *
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListenerAdapter listenerAdapter,
                                                   MessageListenerAdapter listenerAdapterWang,
                                                   MessageListenerAdapter listenerAdapterTest2,
                                                   MessageListenerAdapter listenerAdapterSubcribe) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //（不同的监听器可以收到同一个频道的信息）接受消息的频道
        container.addMessageListener(listenerAdapter, new PatternTopic("phone"));
        container.addMessageListener(listenerAdapterWang, new PatternTopic("phone"));
        container.addMessageListener(listenerAdapterTest2, new PatternTopic("phoneTest2"));
        container.addMessageListener(listenerAdapterSubcribe, new PatternTopic("SubcribeTopic"));
        return container;
    }

    /**
     * 绑定消息监听者和接收监听的方法
     *
     * @param receiver
     * @return
     */
    @Bean
    public MessageListenerAdapter listenerAdapter(ReceiverRedisMessage receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    public MessageListenerAdapter listenerAdapterWang(ReceiverRedisMessage receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessageWang");
    }

    /**
     * 绑定消息监听者和接收监听的方法
     *
     * @param receiver
     * @return
     */
    @Bean
    public MessageListenerAdapter listenerAdapterTest2(ReceiverRedisMessage receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage2");
    }

    /**
     * 注册订阅者
     *
     * @param latch
     * @return
     */
    @Bean
    ReceiverRedisMessage receiver(CountDownLatch latch) {
        return new ReceiverRedisMessage(latch);
    }


    /**
     * 计数器，用来控制线程
     *
     * @return
     */
    @Bean
    public CountDownLatch latch() {
        return new CountDownLatch(1);//指定了计数的次数 1
    }

    /**********************************************/
    /**
     * 绑定消息监听者和接收监听的方法
     *
     * @param subcribeRedisMessage
     * @return
     */
    @Bean
    public MessageListenerAdapter listenerAdapterSubcribe(SubcribeRedisMessage subcribeRedisMessage) {
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(subcribeRedisMessage);
        return messageListenerAdapter;
    }

}
