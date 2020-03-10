package org.example.sbredis.controller;

import org.example.sbredis.service.RedisExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRedisController {

    @Autowired
    private RedisExampleService redisExampleService;


    @RequestMapping(value = "/redis")
    public String redis() {
        //Redis Example
        redisExampleService.save();
        //redisExampleService.saveList();
        //redisExampleService.removeList();
        //redisExampleService.show();
        return "{info: success}";
    }

}
