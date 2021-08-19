package org.example.sbredis.controller;

import org.example.sbredis.service.RedisExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRedisController {

    @Autowired
    private RedisExampleService redisExampleService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @RequestMapping(value = "/redis")
    public String redis() {
        // Redis Example
        // redisExampleService.save();
        // redisExampleService.saveList();
        // redisExampleService.removeList();
        redisExampleService.show();

        // 这个是测试同一个频道，不同的订阅者收到相同的信息，“phone”也就是topic也可以理解为频道
        stringRedisTemplate.convertAndSend("phone", "223333");
        // 这个phoneTest2是另外的一个频道，可以把下面的注释放开同时向phone和phoneTest2这两个topic发送信息看下效果
        stringRedisTemplate.convertAndSend("phoneTest2", "34555665");
        stringRedisTemplate.convertAndSend("SubcribeTopic", "This is a TEST. 这是一个测试!");

        return "{info: success}";
    }

}
