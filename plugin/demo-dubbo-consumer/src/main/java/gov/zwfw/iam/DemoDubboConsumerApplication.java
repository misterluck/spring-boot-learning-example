package gov.zwfw.iam;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableDubbo
public class DemoDubboConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoDubboConsumerApplication.class, args);
    }

}
