package org.example.sbredis.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

public class ReceiverRedisMessage {
    private final static Logger log = LoggerFactory.getLogger(ReceiverRedisMessage.class);

    private CountDownLatch latch;

    @Autowired
    public ReceiverRedisMessage(CountDownLatch latch) {
        this.latch = latch;
    }


    /**
     * 队列消息接收方法
     *
     * @param jsonMsg
     */
    public void receiveMessage(String jsonMsg) {
        System.out.println("[开始消费REDIS消息队列phone数据...]");
        try {
            System.out.println(jsonMsg);
            System.out.println("[消费REDIS消息队列phone数据成功.]");
        } catch (Exception e) {
            log.error("[消费REDIS消息队列phone数据失败，失败信息:{}]", e.getMessage());
        }
        latch.countDown();
    }


    public void receiveMessageWang(String jsonMsg) {
        System.out.println("[王亚南------开始消费REDIS消息队列phone数据...]");
        try {
            System.out.println(jsonMsg);
            System.out.println("[王亚南------消费REDIS消息队列phone数据成功.]");
        } catch (Exception e) {
            log.error("[消费REDIS消息队列phone数据失败，失败信息:{}]", e.getMessage());
        }
        latch.countDown();
    }

    /**
     * 队列消息接收方法
     *
     * @param jsonMsg
     */
    public void receiveMessage2(String jsonMsg) {
        System.out.println("[开始消费REDIS消息队列phoneTest2数据...]");
        try {
            System.out.println(jsonMsg);
            /**
             *  此处执行自己代码逻辑 例如 插入 删除操作数据库等
             */

            System.out.println("[消费REDIS消息队列phoneTest2数据成功.]");
        } catch (Exception e) {
            log.error("[消费REDIS消息队列phoneTest2数据失败，失败信息:{}]", e.getMessage());
        }
        latch.countDown();
    }

}