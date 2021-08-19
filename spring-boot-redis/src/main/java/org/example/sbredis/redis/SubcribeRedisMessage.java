package org.example.sbredis.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class SubcribeRedisMessage implements MessageListener {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("接收数据:"+message.toString());
        System.out.println("订阅频道:"+new String(message.getChannel()));
        
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();

        String msg = (String) stringRedisTemplate.getValueSerializer().deserialize(body);
        String msgchannel = (String) stringRedisTemplate.getValueSerializer().deserialize(channel);
        String msgPattern = new String(pattern);
        System.out.println("==>msg：" + msg + " ==>msgchannel：" + msgchannel + " ==>msgPattern：" + msgPattern);

    }
}
