package org.example.nacos.consumer.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.example.nacos.consumer.dto.Some;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SomeService {

    // 通过负载均衡发现服务，是从发现服务中心拿nacos‐restful‐provider服务列表，通过负载均衡算法获取一个地址
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallbackSome")
    public String getSome() {
        ServiceInstance choose = loadBalancerClient.choose("");
        //服务ip地址
        System.out.println(choose.getHost());
        //服务端口号
        System.out.println(choose.getPort());
        //服务的元数据，返回的是个引用地址
        System.out.println(choose.getMetadata());
        //服务名
        System.out.println(choose.getServiceId());
        //ip:port
        System.out.println(choose.getUri());

        Some some = new Some();
        some.setMessage("你好!");
        some = restTemplate.postForObject("http://spring-cloud-nacos-provider/getSome", some, Some.class);
        return some.getMessage();
    }

    public String fallbackSome(){
        return "some service模块故障";
    }

}
