package org.example.nacos.consumer.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.example.nacos.consumer.dto.Some;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SomeService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallbackSome")
    public String getSome() {
        Some some = new Some();
        some.setMessage("你好!");
        some = restTemplate.postForObject("http://spring-cloud-nacos-provider/getSome", some, Some.class);
        return some.getMessage();
    }

    public String fallbackSome(){
        return "some service模块故障";
    }

}
