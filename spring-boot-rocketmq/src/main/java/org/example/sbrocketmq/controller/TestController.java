package org.example.sbrocketmq.controller;

import org.example.sbrocketmq.producer.MessageExtProducer;
import org.example.sbrocketmq.producer.OrderPaidEventProducer;
import org.example.sbrocketmq.producer.StringProducer;
import org.example.sbrocketmq.producer.UserProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private StringProducer stringProducer;
    @Autowired
    private UserProducer userProducer;
    @Autowired
    private OrderPaidEventProducer orderPaidEventProducer;
    @Autowired
    private MessageExtProducer messageExtProducer;

    @RequestMapping(value = "/test")
    public String test() {
        // stringProducer.syncSend("Hello World!");
        // userProducer.syncSend();
        // orderPaidEventProducer.asyncSend();
        messageExtProducer.convertAndSend();
        return "{'code':'200', 'info':'success'}";
    }

}
