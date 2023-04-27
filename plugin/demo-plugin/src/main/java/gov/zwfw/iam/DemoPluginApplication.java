package gov.zwfw.iam;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
@EnableDubbo
public class DemoPluginApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoPluginApplication.class, args);
    }

    //Java Config声明RestTemplate对象
    //在应用启动时自动执行restTemplate()方法创建RestTemplate对象，其BeanId为restTemplate。
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
