package gov.zwfw.iam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DemoPluginDefaultApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoPluginDefaultApplication.class, args);
    }

}
